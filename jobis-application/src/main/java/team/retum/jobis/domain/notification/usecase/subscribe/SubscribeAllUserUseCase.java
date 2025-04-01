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

        for (Topic topic : topics) { //모든 토픽 하나 당
            for (User user : users) { //모든 deviceToken을 가지고 있는 유저 토픽 구독
                commandNotificationPort.subscribeTopic(user.getToken(), topic);
            }
        }
    }
}
