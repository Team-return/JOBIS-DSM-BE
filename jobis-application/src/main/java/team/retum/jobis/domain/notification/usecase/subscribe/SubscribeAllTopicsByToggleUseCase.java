package team.retum.jobis.domain.notification.usecase.subscribe;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.notification.model.Topic;
import team.retum.jobis.domain.notification.model.TopicSubscription;
import team.retum.jobis.domain.notification.spi.CommandNotificationPort;
import team.retum.jobis.domain.notification.spi.CommandTopicSubscriptionPort;
import team.retum.jobis.domain.notification.spi.QueryTopicSubscriptionPort;
import team.retum.jobis.domain.user.model.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@UseCase
public class SubscribeAllTopicsByToggleUseCase {

    private final CommandNotificationPort commandNotificationPort;
    private final QueryTopicSubscriptionPort queryTopicSubscriptionPort;
    private final CommandTopicSubscriptionPort commandTopicSubscriptionPort;
    private final SecurityPort securityPort;

    public void execute() {
        User user = securityPort.getCurrentUser();

        List<Topic> topics = Arrays.asList(Topic.values());
        boolean hasSubscribed = false;

        for (Topic topic : topics) {
            Optional<TopicSubscription> subscription = queryTopicSubscriptionPort.getByDeviceTokenAndTopic(user.getToken(), topic);
            if (subscription.isPresent() && subscription.get().isSubscribed()) {
                hasSubscribed = true;
                break;
            }
        }

        if (hasSubscribed) {
            topics.forEach(topic -> {
                commandNotificationPort.unsubscribeTopic(user.getToken(), topic);
                commandTopicSubscriptionPort.save(
                    TopicSubscription.builder()
                        .deviceToken(user.getToken())
                        .topic(topic)
                        .isSubscribed(false)
                        .build()
                );
            });
        } else {
            topics.forEach(topic -> {
                commandNotificationPort.subscribeTopic(user.getToken(), topic);
                commandTopicSubscriptionPort.save(
                    TopicSubscription.builder()
                        .deviceToken(user.getToken())
                        .topic(topic)
                        .isSubscribed(true)
                        .build()
                );
            });
        }
    }
}