package team.retum.jobis.domain.notification.spi;

import team.retum.jobis.domain.notification.model.Notification;
import team.retum.jobis.domain.notification.model.Topic;

public interface CommandNotificationPort {

    void saveNotification(Notification notification);

    void unsubscribeTopic(String token, Topic topic);

    void subscribeTopic(String token, Topic topic);
}
