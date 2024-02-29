package team.retum.jobis.domain.notification.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.notification.dto.response.QueryNotificationsResponse;
import team.retum.jobis.domain.notification.dto.response.QueryNotificationsResponse.NotificationResponse;
import team.retum.jobis.domain.notification.spi.QueryNotificationPort;

@RequiredArgsConstructor
@UseCase
public class QueryNotificationsUseCase {

    private final QueryNotificationPort queryNotificationPort;
    private final SecurityPort securityPort;

    public QueryNotificationsResponse execute(Boolean isNew) {
        return new QueryNotificationsResponse(
                queryNotificationPort.queryNotificationsByCondition(securityPort.getCurrentUserId(), isNew).stream()
                        .map(notification ->
                                NotificationResponse.builder()
                                        .notificationId(notification.getId())
                                        .title(notification.getTitle())
                                        .content(notification.getContent())
                                        .isNew(notification.getIsNew())
                                        .topic(notification.getTopic())
                                        .detailId(notification.getDetailId())
                                        .createdAt(notification.getCreatedAt())
                                        .build()
                        ).toList()
        );
    }
}
