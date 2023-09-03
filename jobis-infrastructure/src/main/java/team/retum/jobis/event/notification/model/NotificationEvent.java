package team.retum.jobis.event.notification.model;

import lombok.Getter;
import team.retum.jobis.domain.notification.model.Notification;
import team.retum.jobis.domain.notification.model.Topic;

@Getter
public class NotificationEvent {
    private final Notification notification;

    public NotificationEvent(Notification notification) {
        this.notification = notification;
    }
}
