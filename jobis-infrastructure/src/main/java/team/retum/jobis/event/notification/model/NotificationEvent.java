package team.retum.jobis.event.notification.model;

import lombok.Getter;
import lombok.experimental.SuperBuilder;
import team.retum.jobis.domain.notification.model.Notification;

@Getter
public class NotificationEvent {
    private final Notification notification;

    public NotificationEvent(Notification notification) {
        this.notification = notification;
    }
}
