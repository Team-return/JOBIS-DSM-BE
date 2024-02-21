package team.retum.jobis.event.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import team.retum.jobis.event.notification.model.NotificationEvent;
import team.retum.jobis.thirdparty.fcm.FCMUtil;

@RequiredArgsConstructor
@Component
public class NotificationEventHandler {

    private final FCMUtil fcmUtil;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onNotificationEvent(NotificationEvent event) {
        fcmUtil.sendMessages(event.getNotification(), event.getTokens());
    }
}
