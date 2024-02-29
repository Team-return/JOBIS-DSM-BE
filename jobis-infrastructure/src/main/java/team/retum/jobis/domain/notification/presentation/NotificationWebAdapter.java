package team.retum.jobis.domain.notification.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import team.retum.jobis.domain.notification.dto.response.QueryNotificationsResponse;
import team.retum.jobis.domain.notification.usecase.QueryNotificationsUseCase;
import team.retum.jobis.domain.notification.usecase.ReadNotificationUseCase;

@RequiredArgsConstructor
@RequestMapping("/notifications")
@RestController
public class NotificationWebAdapter {

    private final QueryNotificationsUseCase queryNotificationsUseCase;
    private final ReadNotificationUseCase readNotificationUseCase;

    @GetMapping
    public QueryNotificationsResponse queryNotifications(@RequestParam(value = "is_new", required = false) Boolean isNew) {
        return queryNotificationsUseCase.execute(isNew);
    }

    @PatchMapping("/{notification-id}")
    public void readNotification(@PathVariable("notification-id") Long notificationId) {
        readNotificationUseCase.execute(notificationId);
    }
}
