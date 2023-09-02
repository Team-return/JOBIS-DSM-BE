package team.retum.jobis.common.spi;

import team.retum.jobis.domain.notification.model.Notification;
import team.retum.jobis.domain.user.model.User;

import java.util.List;

public interface PublishNotificationEventPort {

    void publishSingleNotificationEvent(Notification notification, User user);

    void publishGroupNotificationEvent(List<Notification> notifications, List<User> users);
}
