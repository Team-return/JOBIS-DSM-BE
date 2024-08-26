package team.retum.jobis.domain.notification.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.notice.spi.vo.TopicVO;
import team.retum.jobis.domain.notification.persistence.repository.TopicSubscriptionJpaRepository;
import team.retum.jobis.domain.notification.spi.TopicSubscriptionPort;

import java.util.List;

@RequiredArgsConstructor
@Component
public class TopicSubscriptionPersistenceAdapter implements TopicSubscriptionPort {

    private final TopicSubscriptionJpaRepository topicSubscriptionJpaRepository;

    @Override
    public List<TopicVO> getAllByDeviceToken(String deviceToken) {
        return topicSubscriptionJpaRepository.findAllByDeviceToken(deviceToken);
    }
}
