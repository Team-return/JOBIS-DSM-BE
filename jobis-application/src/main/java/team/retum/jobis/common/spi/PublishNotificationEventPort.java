package team.retum.jobis.common.spi;

import team.retum.jobis.domain.notification.model.Notification;

import java.util.List;

public interface PublishNotificationEventPort {

    void publishSingleNotificationEvent(Notification notification, String token);

    void publishGroupNotificationEvent(Notification notification, List<String> tokens);
}
