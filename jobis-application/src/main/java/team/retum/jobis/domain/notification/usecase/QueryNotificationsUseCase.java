package team.retum.jobis.domain.notification.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.notification.dto.response.QueryNotificationsResponse;
import team.retum.jobis.domain.notification.dto.response.QueryNotificationsResponse.NotificationResponse;
import team.retum.jobis.domain.notification.spi.QueryNotificationPort;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class QueryNotificationsUseCase {

    private final QueryNotificationPort queryNotificationPort;
    private final SecurityPort securityPort;

    public QueryNotificationsResponse execute(Boolean isNew) {
        return new QueryNotificationsResponse(
            queryNotificationPort.getByCondition(securityPort.getCurrentUserId(), isNew).stream()
                .map(NotificationResponse::form)
                .toList()
        );
    }
}
