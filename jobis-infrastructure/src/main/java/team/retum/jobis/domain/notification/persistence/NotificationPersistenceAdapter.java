package team.retum.jobis.domain.notification.persistence;

import com.google.firebase.messaging.FirebaseMessaging;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.notification.model.Notification;
import team.retum.jobis.domain.notification.model.Topic;
import team.retum.jobis.domain.notification.persistence.mapper.NotificationMapper;
import team.retum.jobis.domain.notification.persistence.repository.NotificationJpaRepository;
import team.retum.jobis.domain.notification.spi.NotificationPort;
import team.retum.jobis.domain.user.persistence.entity.UserEntity;
import team.retum.jobis.domain.user.persistence.repository.UserJpaRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static team.retum.jobis.domain.notification.persistence.entity.QNotificationEntity.notificationEntity;
import static team.retum.jobis.domain.user.persistence.entity.QUserEntity.userEntity;

@RequiredArgsConstructor
@Component
public class NotificationPersistenceAdapter implements NotificationPort {

    private final NotificationJpaRepository notificationJpaRepository;
    private final UserJpaRepository userJpaRepository;
    private final NotificationMapper notificationMapper;
    private final JPAQueryFactory queryFactory;
    private final FirebaseMessaging firebaseInstance;

    @Override
    public void saveNotification(Notification notification) {
        notificationJpaRepository.save(notificationMapper.toEntity(notification));
    }

    @Override
    public Optional<Notification> queryNotificationById(Long notificationId) {
        return notificationJpaRepository.findById(notificationId)
                .map(notificationMapper::toDomain);
    }

    @Override
    public List<Notification> queryNotificationsByCondition(Long userId, Boolean isNew) {
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
    public void subscribeTopic(String accountId, Topic topic) {
        try {
            firebaseInstance.subscribeToTopicAsync(Arrays.asList(accountId), topic.toString()).get();
            UserEntity userEntity = userJpaRepository.findByAccountId(accountId).orElseThrow();
            userEntity.setIsSubscribed(true);
            userJpaRepository.save(userEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void unsubscribeTopic(String accountId, Topic topic) {
        try {
            firebaseInstance.unsubscribeFromTopicAsync(Arrays.asList(accountId), topic.toString()).get();
            UserEntity userEntity = userJpaRepository.findByAccountId(accountId).orElseThrow();
            userEntity.setIsSubscribed(false);
            userJpaRepository.save(userEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //==condition==//

    private BooleanExpression isNew(Boolean isNew) {
        return isNew == null ? null : notificationEntity.isNew.eq(isNew);
    }
}
