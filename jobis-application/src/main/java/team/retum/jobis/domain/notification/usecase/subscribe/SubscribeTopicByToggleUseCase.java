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

@RequiredArgsConstructor
@UseCase
public class SubscribeTopicByToggleUseCase {

    private final CommandNotificationPort commandNotificationPort;
    private final QueryTopicSubscriptionPort queryTopicSubscriptionPort;
    private final CommandTopicSubscriptionPort commandTopicSubscriptionPort;
    private final SecurityPort securityPort;

    public void execute(Topic topic) {
        User user = securityPort.getCurrentUser();

        queryTopicSubscriptionPort.getByDeviceTokenAndTopic(user.getToken(), topic)
            .ifPresentOrElse(
                subscription -> {
                    commandNotificationPort.unsubscribeTopic(user.getToken(), topic);

                    commandTopicSubscriptionPort.save(
                        TopicSubscription.builder()
                            .deviceToken(user.getToken())
                            .topic(topic)
                            .isSubscribed(false)
                            .build()
                    );
                },
                () -> {
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
