package team.retum.jobis.domain.notification.usecase.subscribe;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.notification.model.Topic;
import team.retum.jobis.domain.notification.spi.CommandNotificationPort;

@RequiredArgsConstructor
@UseCase
public class SubscribeAllTopicsUseCase {

    private final CommandNotificationPort commandNotificationPort;

    public void execute(String token) {
        for (Topic topic: Topic.values()) {
            commandNotificationPort.subscribeTopic(token, topic);
        }
    }
}
