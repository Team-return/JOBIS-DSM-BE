package team.retum.jobis.event.notification.model;

import lombok.Getter;
import lombok.experimental.SuperBuilder;
import team.retum.jobis.domain.notification.model.Notification;

import java.util.List;

@Getter
public class GroupNotificationEvent extends NotificationEvent {
    private final List<String> tokens;

    public GroupNotificationEvent(Notification notification, List<String> tokens) {
        super(notification);
        this.tokens = tokens;
    }
}
