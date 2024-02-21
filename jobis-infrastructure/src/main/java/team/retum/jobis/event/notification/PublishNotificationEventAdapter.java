package team.retum.jobis.event.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import team.retum.jobis.common.spi.PublishNotificationEventPort;
import team.retum.jobis.domain.notification.model.Notification;
import team.retum.jobis.event.notification.model.NotificationEvent;

import java.util.List;

@RequiredArgsConstructor
@Component
public class PublishNotificationEventAdapter implements PublishNotificationEventPort {

    private final ApplicationEventPublisher eventPublisher;

    @Override
    public void publishNotificationEvent(Notification notification, String token) {
        eventPublisher.publishEvent(
                new NotificationEvent(notification, List.of(token))
        );
    }

    @Override
    public void publishNotificationEvent(Notification notification, List<String> tokens) {
        eventPublisher.publishEvent(
                new NotificationEvent(notification, tokens)
        );
    }
}
