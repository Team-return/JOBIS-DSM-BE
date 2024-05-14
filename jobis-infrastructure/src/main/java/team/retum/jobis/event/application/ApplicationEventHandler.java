package team.retum.jobis.event.application;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import team.retum.jobis.domain.application.event.ApplicationsStatusChangedEvent;
import team.retum.jobis.domain.application.model.Application;
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
    private final CommandNotificationPort commandNotificationPort;
    private final QueryUserPort queryUserPort;
    private final FCMUtil fcmUtil;

    @Async("asyncTaskExecutor")
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onApplicationStatusChanged(ApplicationsStatusChangedEvent event) {
        Map<Long, String> userIdTokenMap = queryUserPort.getAllByIds(
                event.getApplications().stream().map(Application::getStudentId).toList()
            ).stream()
            .collect(Collectors.toMap(
                User::getId,
                User::getToken
            ));
        Map<Long, String> companyNameMap = queryCompanyPort.queryCompanyNameByRecruitmentIds(
            event.getApplications().stream().map(Application::getRecruitmentId).toList()
        );
        for (Application application : event.getApplications()) {
            Notification notification = Notification.builder()
                .title(companyNameMap.get(application.getRecruitmentId()))
                .content("지원서 상태가 " + event.getStatus().getName() + "으로 변경되었습니다.")
                .userId(application.getStudentId())
                .topic(Topic.APPLICATION_STATUS_CHANGED)
                .detailId(application.getId())
                .authority(Authority.STUDENT)
                .isNew(true)
                .build();

            commandNotificationPort.saveNotification(notification);
            fcmUtil.sendMessages(
                notification,
                List.of(userIdTokenMap.get(application.getStudentId()))
            );
        }
    }
}
