package team.retum.jobis.domain.notification.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.notice.spi.vo.TopicVO;
import team.retum.jobis.domain.notification.dto.response.QueryTopicsResponse;
import team.retum.jobis.domain.notification.spi.QueryTopicSubscriptionPort;
import team.retum.jobis.domain.user.model.User;

import java.util.List;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class QueryTopicsUseCase {

    private final QueryTopicSubscriptionPort queryTopicSubscriptionPort;
    private final SecurityPort securityPort;

    public QueryTopicsResponse execute() {
        User currentUser = securityPort.getCurrentUser();

        List<TopicVO> topicsVOs = queryTopicSubscriptionPort.getAllByDeviceToken(currentUser.getToken());

        return new QueryTopicsResponse(topicsVOs);
    }
}
