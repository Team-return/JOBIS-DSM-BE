package team.retum.jobis.domain.notification.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import team.retum.jobis.domain.notification.dto.response.QueryNotificationsResponse;
import team.retum.jobis.domain.notification.dto.response.QueryTopicsResponse;
import team.retum.jobis.domain.notification.model.Topic;
import team.retum.jobis.domain.notification.usecase.QueryNotificationsUseCase;
import team.retum.jobis.domain.notification.usecase.QueryTopicsUseCase;
import team.retum.jobis.domain.notification.usecase.ReadNotificationUseCase;
import team.retum.jobis.domain.notification.usecase.subscribe.SubscribeAllTopicsByToggleUseCase;
import team.retum.jobis.domain.notification.usecase.subscribe.SubscribeTopicByToggleUseCase;

@RequiredArgsConstructor
@RequestMapping("/notifications")
@RestController
public class NotificationWebAdapter {

    private final QueryNotificationsUseCase queryNotificationsUseCase;
    private final ReadNotificationUseCase readNotificationUseCase;
    private final SubscribeTopicByToggleUseCase subscribeTopicByToggleUseCase;
    private final SubscribeAllTopicsByToggleUseCase subscribeAllTopicsByToggleUseCase;
    private final QueryTopicsUseCase queryTopicsUseCase;


    @GetMapping
    public QueryNotificationsResponse queryNotifications(@RequestParam(value = "is_new", required = false) Boolean isNew) {
        return queryNotificationsUseCase.execute(isNew);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{notification-id}")
    public void readNotification(@PathVariable("notification-id") Long notificationId) {
        readNotificationUseCase.execute(notificationId);
    }

    @PostMapping("/topic")
    public void subscribeTopic(@RequestParam("topic") Topic topic) {
        subscribeTopicByToggleUseCase.execute(topic);
    }

    @PostMapping("/topics")
    public void subscribeAllTopics() {
        subscribeAllTopicsByToggleUseCase.execute();
    }

    @GetMapping("/topic")
    public QueryTopicsResponse queryTopics() {
        return queryTopicsUseCase.execute();
    }
}
