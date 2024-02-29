package team.retum.jobis.domain.notification.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.domain.notification.model.Topic;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class QueryNotificationsResponse {

    private final List<NotificationResponse> notifications;

    @Getter
    @Builder
    public static class NotificationResponse {
        private final Long notificationId;
        private final String title;
        private final String content;
        private final boolean isNew;
        private final Topic topic;
        private final Long detailId;
        private final LocalDateTime createdAt;
    }
}
