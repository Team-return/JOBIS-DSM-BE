package team.retum.jobis.event.notification.model;

import lombok.Getter;
import team.retum.jobis.domain.notification.model.Notification;

@Getter
public class SingleNotificationEvent extends NotificationEvent {
    private final String token;

    public SingleNotificationEvent(Notification notification, String token) {
        super(notification);
        this.token = token;
    }
}
