package team.retum.jobis.event.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import team.retum.jobis.common.spi.PublishNotificationEventPort;
import team.retum.jobis.domain.notification.model.Topic;
import team.retum.jobis.domain.user.model.User;
import team.retum.jobis.event.notification.model.GroupNotificationEvent;
import team.retum.jobis.event.notification.model.SingleNotificationEvent;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class PublishNotificationEventAdapter implements PublishNotificationEventPort {

    private final ApplicationEventPublisher eventPublisher;

    @Override
    public void publishSingleNotificationEvent(Topic topic, Long detailId, User user) {
        eventPublisher.publishEvent(
                new SingleNotificationEvent(notification, token)
        );
    }

    @Override
    public void publishGroupNotificationEvent(Topic topic, Map<Long, List<User>> idUsersMap) {
        eventPublisher.publishEvent(
                new GroupNotificationEvent(notification, tokens)
        );
    }
}
