package team.retum.jobis.event.notification.model;

import lombok.Getter;
import team.retum.jobis.domain.notification.model.Notification;

import java.util.List;

@Getter
public class NotificationEvent {
    private final Notification notification;
    private final List<String> tokens;

    public NotificationEvent(Notification notification, List<String> tokens) {
        this.notification = notification;
        this.tokens = tokens;
    }
}
