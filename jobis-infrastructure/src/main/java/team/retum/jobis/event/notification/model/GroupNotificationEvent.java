package team.retum.jobis.event.notification.model;

import lombok.Getter;
import team.retum.jobis.domain.notification.model.Notification;

import java.util.List;
import java.util.Map;

@Getter
public class GroupNotificationEvent extends NotificationEvent {
    private final List<String> tokens;

    public GroupNotificationEvent(Notification notification, List<String> tokens) {
        super(notification);
        this.tokens = tokens;
    }
}
