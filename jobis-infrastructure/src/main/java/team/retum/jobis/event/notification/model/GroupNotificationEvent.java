package team.retum.jobis.event.notification.model;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
public class GroupNotificationEvent extends NotificationEvent {
    private final List<String> tokens;
}
