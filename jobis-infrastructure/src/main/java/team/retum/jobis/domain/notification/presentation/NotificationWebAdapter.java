package team.retum.jobis.domain.notification.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import team.retum.jobis.domain.notification.dto.response.QueryNotificationsResponse;
import team.retum.jobis.domain.notification.model.Topic;
import team.retum.jobis.domain.notification.usecase.QueryNotificationsUseCase;
import team.retum.jobis.domain.notification.usecase.ReadNotificationUseCase;
import team.retum.jobis.domain.notification.usecase.subscribe.SubscribeAllTopicsUseCase;
import team.retum.jobis.domain.notification.usecase.subscribe.SubscribeTopicUseCase;
import team.retum.jobis.domain.notification.usecase.subscribe.UnsubscribeAllTopicsUseCase;
import team.retum.jobis.domain.notification.usecase.subscribe.UnsubscribeTopicUseCase;

@RequiredArgsConstructor
@RequestMapping("/notifications")
@RestController
public class NotificationWebAdapter {

    private final QueryNotificationsUseCase queryNotificationsUseCase;
    private final ReadNotificationUseCase readNotificationUseCase;
    private final UnsubscribeTopicUseCase unsubscribeTopicUseCase;
    private final SubscribeTopicUseCase subscribeTopicUseCase;
    private final SubscribeAllTopicsUseCase subscribeAllTopicsUseCase;
    private final UnsubscribeAllTopicsUseCase unsubscribeAllTopicsUseCase;

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
    public void subscribeTopic(@RequestParam("topic") Topic topic, @RequestParam("token") String token) {
        subscribeTopicUseCase.execute(token, topic);
    }

    @DeleteMapping("/topic")
    public void unsubscribeTopic(@RequestParam("topic") Topic topic, @RequestParam("token") String token) {
        unsubscribeTopicUseCase.execute(token, topic);
    }

    @PostMapping("/topics")
    public void subscribeAllTopics(@RequestParam("token") String token) {
        subscribeAllTopicsUseCase.execute(token);
    }

    @DeleteMapping("/topics")
    public void unsubscribeAllTopics(@RequestParam("token") String token) {
        unsubscribeAllTopicsUseCase.execute(token);
    }
}
