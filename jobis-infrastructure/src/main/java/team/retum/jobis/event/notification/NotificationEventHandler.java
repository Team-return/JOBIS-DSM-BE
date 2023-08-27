package team.retum.jobis.event.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;
import team.retum.jobis.domain.notification.spi.NotificationPort;
import team.retum.jobis.event.notification.model.GroupNotificationEvent;
import team.retum.jobis.event.notification.model.NotificationEvent;
import team.retum.jobis.event.notification.model.SingleNotificationEvent;
import team.retum.jobis.thirdparty.fcm.FCMUtil;

@RequiredArgsConstructor
@Component
public class NotificationEventHandler {

    private final FCMUtil fcmUtil;

    @TransactionalEventListener
    public void onNotificationEvent(NotificationEvent event) {
        if (event instanceof SingleNotificationEvent singleEvent) {
            fcmUtil.sendMessage(singleEvent.getNotification(), singleEvent.getToken());
        } else if (event instanceof GroupNotificationEvent groupEvent) {
            fcmUtil.sendMessages(groupEvent.getNotification(), groupEvent.getTokens());
        }
    }
}
