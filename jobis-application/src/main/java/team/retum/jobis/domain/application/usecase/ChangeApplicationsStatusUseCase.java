package team.retum.jobis.domain.application.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.PublishNotificationEventPort;
import team.retum.jobis.domain.application.exception.ApplicationNotFoundException;
import team.retum.jobis.domain.application.model.Application;
import team.retum.jobis.domain.application.model.ApplicationStatus;
import team.retum.jobis.domain.application.spi.CommandApplicationPort;
import team.retum.jobis.domain.application.spi.QueryApplicationPort;
import team.retum.jobis.domain.auth.model.Authority;
import team.retum.jobis.domain.notification.model.Notification;
import team.retum.jobis.domain.notification.model.Topic;
import team.retum.jobis.domain.notification.spi.CommandNotificationPort;
import team.retum.jobis.domain.recruitment.spi.QueryRecruitmentPort;
import team.retum.jobis.domain.user.model.User;
import team.retum.jobis.domain.user.spi.QueryUserPort;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@UseCase
public class ChangeApplicationsStatusUseCase {

    private final QueryApplicationPort queryApplicationPort;
    private final CommandApplicationPort commandApplicationPort;
    private final QueryRecruitmentPort queryRecruitmentPort;
    private final CommandNotificationPort commandNotificationPort;
    private final PublishNotificationEventPort publishNotificationEventPort;
    private final QueryUserPort queryUserPort;

    public void execute(List<Long> applicationIds, ApplicationStatus status) {
        List<Application> applications = queryApplicationPort.queryApplicationsByIds(applicationIds);

        if (applicationIds.size() != applications.size()) {
            throw ApplicationNotFoundException.EXCEPTION;
        }

        commandApplicationPort.changeApplicationStatus(status, applicationIds);

        List<String> tokens = queryUserPort.queryUsersByIds(
                        applications.stream().map(Application::getStudentId).toList()
                ).stream()
                .map(User::getToken)
                .toList();
        Map<Long, String> companyNameMap = queryRecruitmentPort.queryCompanyNameByRecruitmentIds(
                applications.stream().map(Application::getRecruitmentId).toList()
        );
        for (Application application : applications) {
            Notification notification = Notification.builder()
                    .title(companyNameMap.get(application.getRecruitmentId()))
                    .content("지원서 상태가 {" + status.name() + "}로 변경되었습니다.")
                    .userId(application.getStudentId())
                    .topic(Topic.APPLICATION_STATUS_CHANGED)
                    .detailId(application.getId())
                    .authority(Authority.STUDENT)
                    .isNew(true)
                    .build();

            commandNotificationPort.saveNotification(notification);
            publishNotificationEventPort.publishNotificationEvent(notification, tokens);
        }
    }
}
