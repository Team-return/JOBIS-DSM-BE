package team.retum.jobis.domain.notification.spi;

import team.retum.jobis.domain.notification.model.Notification;

public interface CommandNotificationPort {

    void saveNotification(Notification notification);
}
