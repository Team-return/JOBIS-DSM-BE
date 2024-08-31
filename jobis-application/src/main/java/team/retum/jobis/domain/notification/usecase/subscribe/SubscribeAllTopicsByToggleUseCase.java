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

import java.util.Arrays;

@RequiredArgsConstructor
@UseCase
public class SubscribeAllTopicsByToggleUseCase {

    private final CommandNotificationPort commandNotificationPort;
    private final QueryTopicSubscriptionPort queryTopicSubscriptionPort;
    private final CommandTopicSubscriptionPort commandTopicSubscriptionPort;
    private final SecurityPort securityPort;

    public void execute() {
        User user = securityPort.getCurrentUser();
        boolean isSubscribed = Arrays.stream(Topic.values())
            .anyMatch(topic -> queryTopicSubscriptionPort.getByDeviceTokenAndTopic(user.getToken(), topic).isPresent());

        if (isSubscribed) {
            Arrays.stream(Topic.values()).forEach(topic -> {
                commandNotificationPort.unsubscribeTopic(user.getToken(), topic);
                commandTopicSubscriptionPort.saveTopicSubscription(
                    TopicSubscription.builder()
                        .deviceToken(user.getToken())
                        .topic(topic)
                        .isSubscribed(false)
                        .build()
                );
            });
        } else {
            Arrays.stream(Topic.values()).forEach(topic -> {
                commandNotificationPort.subscribeTopic(user.getToken(), topic);
                commandTopicSubscriptionPort.saveTopicSubscription(
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
