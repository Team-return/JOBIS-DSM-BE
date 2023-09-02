package team.retum.jobis.domain.notification.spi;

import team.retum.jobis.domain.notification.model.Notification;

import java.util.List;

public interface CommandNotificationPort {

    void saveAllNotification(List<Notification> notifications);
    void saveNotification(Notification notification);
}
