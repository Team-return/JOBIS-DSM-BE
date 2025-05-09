package team.retum.jobis.domain.notification.usecase.subscribe;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.notification.model.Topic;
import team.retum.jobis.domain.notification.spi.CommandNotificationPort;
import team.retum.jobis.domain.user.model.User;
import team.retum.jobis.domain.user.spi.QueryUserPort;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@UseCase
public class SubscribeAllUserUseCase {

    private final CommandNotificationPort commandNotificationPort;
    private final QueryUserPort queryUserPort;

    public void execute() {
        List<User> users = queryUserPort.getAllByDeviceTokenExists();
        List<Topic> topics = Arrays.asList(Topic.values());

        for (Topic topic : topics) {
            for (User user : users) {
                commandNotificationPort.subscribeTopic(user.getToken(), topic);
            }
        }
    }
}
