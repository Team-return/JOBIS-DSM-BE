package team.retum.jobis.domain.notification.persistence;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.notice.spi.vo.TopicVO;
import team.retum.jobis.domain.notification.model.Topic;
import team.retum.jobis.domain.notification.model.TopicSubscription;
import team.retum.jobis.domain.notification.persistence.mapper.TopicSubscriptionMapper;
import team.retum.jobis.domain.notification.persistence.repository.TopicSubscriptionJpaRepository;
import team.retum.jobis.domain.notification.spi.TopicSubscriptionPort;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static team.retum.jobis.domain.notification.persistence.entity.QTopicSubscriptionEntity.topicSubscriptionEntity;

@RequiredArgsConstructor
@Component
public class TopicSubscriptionPersistenceAdapter implements TopicSubscriptionPort {

    private final TopicSubscriptionJpaRepository topicSubscriptionJpaRepository;
    private final TopicSubscriptionMapper topicSubscriptionMapper;
    private final JPAQueryFactory queryFactory;

    @Override
    public List<TopicVO> getAllByDeviceToken(String deviceToken) {
        if (!topicSubscriptionJpaRepository.existsByDeviceToken(deviceToken)) {
            saveTopics(deviceToken);
        }
        return topicSubscriptionJpaRepository.findAllByDeviceToken(deviceToken);
    }

    @Override
    public Optional<TopicSubscription> getByDeviceTokenAndTopic(String deviceToken, Topic topic) {
        return Optional.ofNullable(
                queryFactory
                    .selectFrom(topicSubscriptionEntity)
                    .where(
                        topicSubscriptionEntity.deviceToken.eq(deviceToken),
                        topicSubscriptionEntity.topic.eq(topic),
                        topicSubscriptionEntity.isSubscribed.isTrue()
                    )
                    .fetchOne()
            )
            .map(topicSubscriptionMapper::toDomain);
    }

    @Override
    public void save(TopicSubscription topicSubscription) {
        topicSubscriptionJpaRepository.save(topicSubscriptionMapper.toEntity(topicSubscription));
    }

    @Override
    public void saveTopics(String deviceToken) {
        Arrays.stream(Topic.values()).forEach(topic -> {
            save(
                TopicSubscription.builder()
                    .deviceToken(deviceToken)
                    .topic(topic)
                    .isSubscribed(false)
                    .build()
            );
        });
    }
}
