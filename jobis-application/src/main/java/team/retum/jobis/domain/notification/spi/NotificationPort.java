package team.retum.jobis.domain.notification.spi;

import team.retum.jobis.domain.notification.model.Notification;

import java.util.List;

public interface NotificationPort {

    void sendMessage(Notification notification, String token);

    void sendMessages(Notification notification, List<String> tokens);
}
