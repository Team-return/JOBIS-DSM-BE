package team.retum.jobis.domain.notification.persistence.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.notification.model.TopicSubscription;
import team.retum.jobis.domain.notification.persistence.entity.TopicSubscriptionEntity;

@RequiredArgsConstructor
@Component
public class TopicSubscriptionMapper {

    public TopicSubscriptionEntity toEntity(TopicSubscription topicSubscription) {
        return TopicSubscriptionEntity.builder()
            .deviceToken(topicSubscription.getDeviceToken())
            .topic(topicSubscription.getTopic())
            .isSubscribed(topicSubscription.isSubscribed())
            .build();
    }

    public TopicSubscription toDomain(TopicSubscriptionEntity topicSubscriptionEntity) {
        return TopicSubscription.builder()
            .deviceToken(topicSubscriptionEntity.getDeviceToken())
            .topic(topicSubscriptionEntity.getTopic())
            .isSubscribed(topicSubscriptionEntity.isSubscribed())
            .build();
    }
}
