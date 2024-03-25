package team.retum.jobis.domain.notification.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.notification.exception.NotificationNotFoundException;
import team.retum.jobis.domain.notification.model.Notification;
import team.retum.jobis.domain.notification.spi.CommandNotificationPort;
import team.retum.jobis.domain.notification.spi.QueryNotificationPort;

@RequiredArgsConstructor
@UseCase
public class ReadNotificationUseCase {

    private final QueryNotificationPort queryNotificationPort;
    private final CommandNotificationPort commandNotificationPort;

    public void execute(Long notificationId) {
        Notification notification = queryNotificationPort.queryNotificationById(notificationId)
            .orElseThrow(() -> NotificationNotFoundException.EXCEPTION);

        commandNotificationPort.saveNotification(notification.read());
    }
}
