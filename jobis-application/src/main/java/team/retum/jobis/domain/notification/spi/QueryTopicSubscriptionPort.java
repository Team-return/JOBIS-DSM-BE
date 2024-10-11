package team.retum.jobis.domain.notification.spi;

import team.retum.jobis.domain.notice.spi.vo.TopicVO;
import team.retum.jobis.domain.notification.model.Topic;
import team.retum.jobis.domain.notification.model.TopicSubscription;

import java.util.List;
import java.util.Optional;

public interface QueryTopicSubscriptionPort {

    List<TopicVO> getAllByDeviceToken(String deviceToken);

    Optional<TopicSubscription> getByDeviceTokenAndTopic(String deviceToken, Topic topic);

}
