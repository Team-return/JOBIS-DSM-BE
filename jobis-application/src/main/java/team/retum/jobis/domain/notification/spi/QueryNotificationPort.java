package team.retum.jobis.domain.notification.spi;

import team.retum.jobis.domain.notification.model.Notification;

import java.util.List;
public interface QueryNotificationPort {
    List<Notification> queryNotificationsByCondition(Long userId, Boolean isNew);
}
