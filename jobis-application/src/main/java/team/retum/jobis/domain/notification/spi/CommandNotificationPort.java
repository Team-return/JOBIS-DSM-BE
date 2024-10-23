package team.retum.jobis.domain.notification.spi;

import team.retum.jobis.domain.notification.model.Notification;
import team.retum.jobis.domain.notification.model.Topic;
import team.retum.jobis.domain.user.model.User;

public interface CommandNotificationPort {

    void save(Notification notification);

    void unsubscribeTopic(String token, Topic topic);

    void subscribeTopic(String token, Topic topic);

    void subscribeAllTopic(User user);
}
