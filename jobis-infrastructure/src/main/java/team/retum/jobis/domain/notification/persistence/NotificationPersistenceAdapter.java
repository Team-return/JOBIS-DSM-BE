package team.retum.jobis.domain.notification.persistence;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.notification.model.Notification;
import team.retum.jobis.domain.notification.model.Topic;
import static team.retum.jobis.domain.notification.persistence.entity.QNotificationEntity.notificationEntity;

import team.retum.jobis.domain.notification.model.TopicSubscription;
import team.retum.jobis.domain.notification.persistence.mapper.NotificationMapper;
import team.retum.jobis.domain.notification.persistence.repository.NotificationJpaRepository;
import team.retum.jobis.domain.notification.spi.CommandNotificationPort;
import team.retum.jobis.domain.notification.spi.CommandTopicSubscriptionPort;
import team.retum.jobis.domain.notification.spi.NotificationPort;
import static team.retum.jobis.domain.user.persistence.entity.QUserEntity.userEntity;

import team.retum.jobis.domain.user.model.User;
import team.retum.jobis.thirdparty.fcm.FCMUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class NotificationPersistenceAdapter implements NotificationPort {

    private final NotificationJpaRepository notificationJpaRepository;
    private final CommandTopicSubscriptionPort commandTopicSubscriptionPort;
    private final CommandNotificationPort commandNotificationPort;
    private final NotificationMapper notificationMapper;
    private final JPAQueryFactory queryFactory;
    private final FCMUtil fcmUtil;

    @Override
    public void save(Notification notification) {
        notificationJpaRepository.save(notificationMapper.toEntity(notification));
    }

    @Override
    public Optional<Notification> getById(Long notificationId) {
        return notificationJpaRepository.findById(notificationId)
            .map(notificationMapper::toDomain);
    }

    @Override
    public List<Notification> getByCondition(Long userId, Boolean isNew) {
        return queryFactory
            .selectFrom(notificationEntity)
            .join(notificationEntity.userEntity, userEntity)
            .where(
                userEntity.id.eq(userId),
                isNew(isNew)
            )
            .orderBy(notificationEntity.createdAt.desc())
            .fetch().stream()
            .map(notificationMapper::toDomain)
            .toList();
    }

    @Override
    public void subscribeTopic(String token, Topic topic) {
        fcmUtil.subscribeTopic(token, topic);
    }

    @Override
    public void subscribeAllTopic(User user) {
        Arrays.stream(Topic.values()).forEach(topic -> {
            commandNotificationPort.subscribeTopic(user.getToken(), topic);
            commandTopicSubscriptionPort.save(
                TopicSubscription.builder()
                    .deviceToken(user.getToken())
                    .topic(topic)
                    .isSubscribed(true)
                    .build()
            );
        });
    }

    @Override
    public void unsubscribeTopic(String token, Topic topic) {
        fcmUtil.unsubscribeTopic(token, topic);
    }

    //==condition==//

    private BooleanExpression isNew(Boolean isNew) {
        return isNew == null ? null : notificationEntity.isNew.eq(isNew);
    }
}
