package team.retum.jobis.event.application;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import team.retum.jobis.domain.application.event.ApplicationsStatusChangedEvent;
import team.retum.jobis.domain.application.event.SingleApplicationStatusChangedEvent;
import team.retum.jobis.domain.application.model.Application;
import team.retum.jobis.domain.application.model.ApplicationStatus;
import team.retum.jobis.domain.auth.model.Authority;
import team.retum.jobis.domain.company.spi.QueryCompanyPort;
import team.retum.jobis.domain.notification.model.Notification;
import team.retum.jobis.domain.notification.model.Topic;
import team.retum.jobis.domain.notification.spi.CommandNotificationPort;
import team.retum.jobis.domain.user.model.User;
import team.retum.jobis.domain.user.spi.QueryUserPort;
import team.retum.jobis.thirdparty.fcm.FCMUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class ApplicationEventHandler {

    private final QueryCompanyPort queryCompanyPort;
    private final QueryUserPort queryUserPort;
    private final CommandNotificationPort commandNotificationPort;
    private final FCMUtil fcmUtil;

    @Async("asyncTaskExecutor")
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onSingleApplicationStatusChanged(ApplicationsStatusChangedEvent event) {
        if (event.getStatus() == ApplicationStatus.PROCESSING) {
            return;
        }
        Map<Long, String> userIdTokenMap = queryUserPort.getAllByIds(
                event.getApplications().stream().map(Application::getStudentId).toList()
            ).stream()
            .collect(Collectors.toMap(
                User::getId,
                User::getToken
            ));
        Map<Long, String> companyNameMap = queryCompanyPort.getCompanyNameByRecruitmentIds(
            event.getApplications().stream().map(Application::getRecruitmentId).toList()
        );
        for (Application application : event.getApplications()) {
            String companyName = companyNameMap.get(application.getRecruitmentId());

            ApplicationMessage notificationMessage = ApplicationMessage.of(event, companyName);

            Notification notification = Notification.builder()
                .title(notificationMessage.getTitle())
                .content(notificationMessage.getContent())
                .userId(application.getStudentId())
                .topic(Topic.APPLICATION)
                .detailId(application.getId())
                .authority(Authority.STUDENT)
                .isNew(true)
                .build();

            commandNotificationPort.save(notification);
            fcmUtil.sendMessages(
                notification,
                List.of(userIdTokenMap.get(application.getStudentId()))
            );
        }
    }

    @Async("asyncTaskExecutor")
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onApplicationStatusChange(SingleApplicationStatusChangedEvent event) {
        User user = queryUserPort.getByStudentId(event.getApplication().getStudentId());

        Notification notification = Notification.builder()
            .title("결과 보러가기")
            .content("지원서 상태가 " + event.getStatus().getName() + "으로 변경되었습니다.")
            .userId(event.getApplication().getStudentId())
            .deviceToken(user.getToken())
            .topic(Topic.APPLICATION)
            .detailId(event.getApplication().getId())
            .authority(Authority.STUDENT)
            .isNew(true)
            .build();

        commandNotificationPort.save(notification);
        fcmUtil.sendMessages(
            notification,
            List.of(user.getToken())
        );
    }
}
