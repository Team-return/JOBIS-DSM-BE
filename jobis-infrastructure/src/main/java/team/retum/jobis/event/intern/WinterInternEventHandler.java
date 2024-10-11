package team.retum.jobis.event.intern;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import team.retum.jobis.domain.auth.model.Authority;
import team.retum.jobis.domain.intern.event.WinterInternToggledEvent;
import team.retum.jobis.domain.notification.model.Notification;
import team.retum.jobis.domain.notification.model.Topic;
import team.retum.jobis.domain.notification.spi.CommandNotificationPort;
import team.retum.jobis.domain.user.model.User;
import team.retum.jobis.domain.user.spi.QueryUserPort;
import team.retum.jobis.thirdparty.fcm.FCMUtil;

import java.util.List;

@RequiredArgsConstructor
@Component
public class WinterInternEventHandler {

    private final FCMUtil fcmUtil;
    private final CommandNotificationPort commandNotificationPort;
    private final QueryUserPort queryUserPort;

    @Async("asyncTaskExecutor")
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onWinterInternToggled(WinterInternToggledEvent event) {
        List<String> deviceTokens = queryUserPort.getDeviceTokenByTopic(Topic.WINTER_INTERN_STATUS_CHANGED);

        deviceTokens.forEach(deviceToken -> {
            User user = queryUserPort.getUserIdByDeviceToken(deviceToken);

            if (event.getWinterIntern().isWinterInterned()) {
                Notification notification = Notification.builder()
                    .title("겨울인턴 시즌이 다가왔어요~")
                    .content("오늘부터 체험형 현장실습을 지원하실 수 있어요.")
                    .userId(user.getId())
                    .detailId(0L)
                    .topic(Topic.WINTER_INTERN_STATUS_CHANGED)
                    .authority(Authority.STUDENT)
                    .isNew(true)
                    .build();

                commandNotificationPort.save(notification);

                fcmUtil.sendMessageToTopic(notification);
            }
        });
    }
}
