package team.retum.jobis.domain.notification.model;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.common.annotation.Aggregate;

@Getter
@Builder(toBuilder = true)
@Aggregate
public class TopicSubscription {

    private final String deviceToken;

    private final Topic topic;

    private final boolean isSubscribed;
}
