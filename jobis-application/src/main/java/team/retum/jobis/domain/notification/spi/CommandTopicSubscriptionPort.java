package team.retum.jobis.domain.notification.spi;

import team.retum.jobis.domain.notification.model.TopicSubscription;

public interface CommandTopicSubscriptionPort {

    void save(TopicSubscription topicSubscription);
}
