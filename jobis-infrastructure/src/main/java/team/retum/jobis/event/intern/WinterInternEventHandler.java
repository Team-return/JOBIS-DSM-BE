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
import team.retum.jobis.thirdparty.fcm.FCMUtil;

@RequiredArgsConstructor
@Component
public class WinterInternEventHandler {

    private final FCMUtil fcmUtil;
    private final CommandNotificationPort commandNotificationPort;

    @Async("asyncTaskExecutor")
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onWinterInternToggled(WinterInternToggledEvent event) {
        if (event.getWinterIntern().isWinterInterned()) {
            Notification notification = Notification.builder()
                    .title("겨울인턴 지원 활성화")
                    .content("겨울인턴이 활성화되었습니다!")
                    .topic(Topic.WINTER_INTERN_STATUS_CHANGED)
                    .detailId(null)
                    .authority(Authority.STUDENT)
                    .isNew(true)
                    .build();

            commandNotificationPort.save(notification);

            fcmUtil.sendMessageToTopic(notification);
        }
    }
}
