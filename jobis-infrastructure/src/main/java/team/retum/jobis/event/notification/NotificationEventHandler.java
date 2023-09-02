package team.retum.jobis.event.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import team.retum.jobis.domain.notification.spi.CommandNotificationPort;
import team.retum.jobis.domain.notification.spi.NotificationPort;
import team.retum.jobis.domain.user.model.User;
import team.retum.jobis.event.notification.model.GroupNotificationEvent;
import team.retum.jobis.event.notification.model.NotificationEvent;
import team.retum.jobis.event.notification.model.SingleNotificationEvent;
import team.retum.jobis.thirdparty.fcm.FCMUtil;

@RequiredArgsConstructor
@Component
public class NotificationEventHandler {

    private final FCMUtil fcmUtil;
    private final CommandNotificationPort commandNotificationPort;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onNotificationEvent(NotificationEvent event) {
        if (event instanceof SingleNotificationEvent singleEvent) {
            commandNotificationPort.saveNotification(singleEvent.getNotification());
            fcmUtil.sendMessage(singleEvent.getNotification(), singleEvent.getUser().getDeviceToken());
        } else if (event instanceof GroupNotificationEvent groupEvent) {
            commandNotificationPort.saveAllNotification(groupEvent.getNotifications());
            fcmUtil.sendMessages(
                    groupEvent.getNotifications().get(0),
                    groupEvent.getUsers().stream().map(User::getDeviceToken).toList()
            );
        }
    }
}
