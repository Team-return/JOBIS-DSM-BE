package team.retum.jobis.domain.notification.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import team.retum.jobis.domain.notice.spi.vo.TopicVO;
import team.retum.jobis.domain.notification.persistence.entity.TopicSubscriptionEntity;
import team.retum.jobis.domain.notification.persistence.entity.TopicSubscriptionId;

import java.util.List;

public interface TopicSubscriptionJpaRepository extends CrudRepository<TopicSubscriptionEntity, TopicSubscriptionId> {

    List<TopicVO> findAllByDeviceToken(String deviceToken);

}
