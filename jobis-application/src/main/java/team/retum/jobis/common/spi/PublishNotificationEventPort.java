package team.retum.jobis.common.spi;

import team.retum.jobis.domain.notification.model.Notification;

import java.util.List;

public interface PublishNotificationEventPort {
    void publishNotificationEvent(Notification notification, String token);

    void publishNotificationEvent(Notification notification, List<String> tokens);
}
