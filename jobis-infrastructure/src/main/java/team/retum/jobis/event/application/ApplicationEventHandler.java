package team.retum.jobis.event.application;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import team.retum.jobis.domain.application.event.ApplicationsStatusChangedEvent;
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
    private final CommandNotificationPort commandNotificationPort;
    private final QueryUserPort queryUserPort;
    private final FCMUtil fcmUtil;

    @Async("asyncTaskExecutor")
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onApplicationStatusChanged(ApplicationsStatusChangedEvent event) {
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

            setNotificationMessage(event, companyName);

            Notification notification = Notification.builder()
                .title(title)
                .content(content)
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

    private void setNotificationMessage(ApplicationsStatusChangedEvent event, String companyName) {

        switch(event.getStatus()) {
            case REQUESTED:
                title = "지원서 제출 완료";
                content = "지원서가 제출되었습니다. 검토가 진행될 예정입니다. 조금만 기다려 주세요!";
                break;

            case APPROVED:
                title = "지원서가 승인되었습니다!";
                content = "지원서가 승인되었어요. 곧 회사에 전송될 거예요!";
                break;

            case SEND:
                title = "지원서 전송 완료";
                content = "'" + companyName + "'에 지원서가 성공적으로 전송되었습니다. 좋은 결과를 기원합니다!";
                break;

            case PROCESSING:
                title = "지원서가 진행중입니다";
                content = "'" + companyName + "'에서 지원서를 검토 중입니다. 진행 상황을 계속 확인해 주세요.";
                break;

            case FAILED:
                title = companyName + "지원서 탈락";
                content = "아쉽게도 '" + companyName + "'의 지원에서 탈락하셨습니다. 다음 기회에 더 좋은 결과가 있길 바랍니다.";
                break;

            case PASS:
                title = "(취뽀) " + companyName + "에 합격하셨습니다!! 🥳";
                content = "합격을 진심으로 축하드립니다.";
                break;

            case FIELD_TRAIN:
                title = "현장실습이 확정되었습니다!";
                content = "'" + companyName + "'에서 현장실습이 확정되었습니다. 새로운 경험을 통해 성장하시길 바랍니다!";
                break;

            case ACCEPTANCE:
                title = "근로계약이 체결됬습니다!";
                content = "'" + companyName + "'과의 근로계약이 체결됬습니다. 새로운 여정을 응원합니다!";
                break;

            case REJECTED:
                title = "지원서가 반려되었습니다.";
                content = "지원서가 '" + companyName + "'에 의해 반려되었습니다. 지원서 내용을 다시 확인하고 수정 후 재제출해 주세요.";
                break;

            default:
                title = "지원서 상태가 변경되었습니다";
                content = "지원서 상태가 " + event.getStatus().getName() + "으로 변경되었습니다.";
                break;
        }
    }
}
