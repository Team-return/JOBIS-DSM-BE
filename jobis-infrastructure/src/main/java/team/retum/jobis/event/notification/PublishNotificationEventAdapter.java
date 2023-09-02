package team.retum.jobis.event.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import team.retum.jobis.common.spi.PublishNotificationEventPort;
import team.retum.jobis.domain.notification.model.Notification;
import team.retum.jobis.domain.user.model.User;
import team.retum.jobis.event.notification.model.GroupNotificationEvent;
import team.retum.jobis.event.notification.model.SingleNotificationEvent;

import java.util.List;

@RequiredArgsConstructor
@Component
public class PublishNotificationEventAdapter implements PublishNotificationEventPort {

    private final ApplicationEventPublisher eventPublisher;

    @Override
    public void publishSingleNotificationEvent(Notification notification, User user) {
        eventPublisher.publishEvent(
                new SingleNotificationEvent(notification, token)
        );
    }

    @Override
    public void publishGroupNotificationEvent(List<Notification> notifications, List<User> users) {
        eventPublisher.publishEvent(
                new GroupNotificationEvent(notification, tokens)
        );
    }
}
