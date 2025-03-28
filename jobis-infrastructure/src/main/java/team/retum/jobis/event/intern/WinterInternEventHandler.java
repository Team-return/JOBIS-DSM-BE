package team.retum.jobis.event.intern;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import team.retum.jobis.domain.auth.model.Authority;
import team.retum.jobis.domain.company.exception.CompanyNotFoundException;
import team.retum.jobis.domain.company.model.Company;
import team.retum.jobis.domain.company.spi.QueryCompanyPort;
import team.retum.jobis.domain.intern.event.WinterInternRegisteredEvent;
import team.retum.jobis.domain.intern.event.WinterInternToggledEvent;
import team.retum.jobis.domain.notification.model.Notification;
import team.retum.jobis.domain.notification.model.Topic;
import team.retum.jobis.domain.notification.spi.CommandNotificationPort;
import team.retum.jobis.domain.user.model.User;
import team.retum.jobis.domain.user.spi.QueryUserPort;

import java.util.List;

@RequiredArgsConstructor
@Component
public class WinterInternEventHandler {

    private final CommandNotificationPort commandNotificationPort;
    private final QueryUserPort queryUserPort;
    private final QueryCompanyPort queryCompanyPort;

    @Async("asyncTaskExecutor")
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onWinterInternToggled(WinterInternToggledEvent event) {
        List<String> deviceTokens = queryUserPort.getDeviceTokenByTopic(Topic.WINTER_INTERN);

        deviceTokens.forEach(deviceToken -> {
            User user = queryUserPort.getUserIdByDeviceToken(deviceToken);

            if (event.getWinterIntern().isWinterInterned()) {
                Notification notification = Notification.builder()
                    .title("겨울인턴 시즌이 다가왔어요~")
                    .content("오늘부터 체험형 현장실습을 지원하실 있어요.")
                    .userId(user.getId())
                    .detailId(0L)
                    .topic(Topic.WINTER_INTERN)
                    .authority(Authority.STUDENT)
                    .isNew(true)
                    .build();

                commandNotificationPort.save(notification);
            }
        });
    }

    @Async("asyncTaskExecutor")
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onWinterInternRegistered(WinterInternRegisteredEvent event) {
        List<String> deviceTokens = queryUserPort.getDeviceTokenByTopic(Topic.WINTER_INTERN);

        Company company = queryCompanyPort.getById(event.getRecruitment().getCompanyId())
            .orElseThrow(() -> CompanyNotFoundException.EXCEPTION);

        String companyName = company.getName();

        for (String deviceToken : deviceTokens) {
            User user = queryUserPort.getUserIdByDeviceToken(deviceToken);

            Notification notification = Notification.builder()
                .title(companyName + " 겨울 인턴십 모집 공고 ⛄️")
                .content("겨울 인턴십 모집 의뢰서가 등록되었어요. 지금 확인해보세요!")
                .userId(user.getId())
                .detailId(event.getRecruitment().getId())
                .topic(Topic.WINTER_INTERN)
                .authority(Authority.STUDENT)
                .isNew(true)
                .build();

            commandNotificationPort.save(notification);
        }
    }
}
