package team.retum.jobis.domain.notification.spi;

import team.retum.jobis.domain.notification.model.Notification;

import java.util.List;
import java.util.Optional;

public interface QueryNotificationPort {

    Optional<Notification> queryNotificationById(Long notificationId);

    List<Notification> queryNotificationsByCondition(Long userId, Boolean isNew);
}
