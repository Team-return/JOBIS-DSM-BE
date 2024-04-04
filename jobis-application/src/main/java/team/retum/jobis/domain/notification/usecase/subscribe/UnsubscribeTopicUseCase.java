package team.retum.jobis.domain.notification.usecase.subscribe;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.notification.model.Topic;
import team.retum.jobis.domain.notification.spi.CommandNotificationPort;

@RequiredArgsConstructor
@UseCase
public class UnsubscribeTopicUseCase {

    private final CommandNotificationPort commandNotificationPort;

    public void execute(String accountId, Topic topic) {
        commandNotificationPort.unsubscribeTopic(accountId, topic);
    }
}
