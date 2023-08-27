package team.retum.jobis.event.notification.model;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class SingleNotificationEvent extends NotificationEvent {
    private final String token;
}
