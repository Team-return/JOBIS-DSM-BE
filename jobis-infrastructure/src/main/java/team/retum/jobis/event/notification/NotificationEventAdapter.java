package team.retum.jobis.event.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import team.retum.jobis.common.spi.NotificationEventPort;
import team.retum.jobis.domain.notification.model.Notification;
import team.retum.jobis.event.notification.model.GroupNotificationEvent;
import team.retum.jobis.event.notification.model.SingleNotificationEvent;

import java.util.List;

@RequiredArgsConstructor
@Component
public class NotificationEventAdapter implements NotificationEventPort {

    private final ApplicationEventPublisher eventPublisher;

    @Override
    public void publishSingleNotificationEvent(Notification notification, String token) {
        eventPublisher.publishEvent(
                SingleNotificationEvent.builder()
                        .notification(notification)
                        .token(token)
                        .build()
        );
    }

    @Override
    public void publishGroupNotificationEvent(Notification notification, List<String> tokens) {
        eventPublisher.publishEvent(
                GroupNotificationEvent.builder()
                        .notification(notification)
                        .tokens(tokens)
                        .build()
        );
    }
}
