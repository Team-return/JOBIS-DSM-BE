package team.retum.jobis.global.aop;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import team.retum.jobis.common.annotation.NotificationPublish;
import team.retum.jobis.common.spi.PublishNotificationEventPort;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.application.model.Application;
import team.retum.jobis.domain.application.spi.QueryApplicationPort;
import team.retum.jobis.domain.auth.model.Authority;
import team.retum.jobis.domain.bookmark.model.Bookmark;
import team.retum.jobis.domain.bookmark.spi.QueryBookmarkPort;
import team.retum.jobis.domain.notification.model.Notification;
import team.retum.jobis.domain.notification.model.Topic;
import team.retum.jobis.domain.recruitment.model.Recruitment;
import team.retum.jobis.domain.user.exception.UserNotFoundException;
import team.retum.jobis.domain.user.model.User;
import team.retum.jobis.domain.user.spi.QueryUserPort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Component
@Aspect
public class NotificationAspect {

    private final PublishNotificationEventPort publishNotificationEventPort;
    private final QueryUserPort queryUserPort;
    private final QueryApplicationPort queryApplicationPort;
    private final QueryBookmarkPort queryBookmarkPort;


    @AfterReturning(
            value = "@annotation(team.retum.jobis.common.annotation.NotificationPublish) && @annotation(notificationPublish)",
            returning = "returnValue"
    )
    public void publishNotificationEvent(NotificationPublish notificationPublish, Object returnValue) {
        switch (notificationPublish.topic()) {
            case INTERVIEW_SOON -> onInterviewSoon();
            case NEW_APPLICATION -> onNewApplication();
            case NEW_RECRUITMENT -> onNewRecruitment();
            case RECRUITMENT_DONE -> onRecruitmentDone(returnValue);
            case APPLICATION_STATUS_CHANGED -> onApplicationStatusChange(returnValue);
            case RECRUITMENT_STATUS_CHANGED -> onRecruitmentStatusChange(returnValue);
        }
    }

    private void onNewRecruitment() {
        //TODO 메일
    }

    private void onNewApplication() {
        //TODO 메일
    }

    private void onInterviewSoon() {
        //TODO 면접 설계후
    }

    private void onRecruitmentDone(Object returnValue) {
        if (returnValue instanceof Recruitment recruitment) {
            Map<Long, List<User>> map = new HashMap<>();
            List<User> users = queryUserPort.queryUsersByIdIn(
                    queryBookmarkPort.queryBookmarksByRecruitmentId(recruitment.getId())
                            .stream().map(Bookmark::getStudentId).toList()
            );
            map.put(recruitment.getId(), users);

            publishNotificationEventPort.publishGroupNotificationEvent(Topic.RECRUITMENT_DONE, map);
        }
    }

    private void  onRecruitmentStatusChange(Object returnValue) {
        if (returnValue instanceof List<?> list) {
            Map<Long, List<User>> map = new HashMap<>();
            for (Object object : list) {
                if (object instanceof Recruitment recruitment) {
                    List<Application> applications = queryApplicationPort.queryApplicationsByRecruitmentId(recruitment.getId());
                    List<User> users = queryUserPort.queryUsersByIdIn(applications.stream().map(Application::getStudentId).toList());
                    map.put(recruitment.getId(), users);
                }
            }

            publishNotificationEventPort.publishGroupNotificationEvent(Topic.RECRUITMENT_STATUS_CHANGED, map);
        }
    }

    private void onApplicationStatusChange(Object returnValue) {
        if (returnValue instanceof List<?> list) {
            for (Object object : list) {
                if (object instanceof Application application) {
                    User user = queryUserPort.queryUserById(application.getStudentId()).orElseThrow(() -> UserNotFoundException.EXCEPTION);
                    publishNotificationEventPort.publishSingleNotificationEvent(
                            Topic.APPLICATION_STATUS_CHANGED,
                            application.getId(),
                            user
                    );
                }
            }
        }
    }


}
