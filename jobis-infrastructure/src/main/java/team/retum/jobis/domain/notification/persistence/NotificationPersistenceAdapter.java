package team.retum.jobis.domain.notification.persistence;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.notification.model.Notification;
import team.retum.jobis.domain.notification.persistence.mapper.NotificationMapper;
import team.retum.jobis.domain.notification.persistence.repository.NotificationJpaRepository;
import team.retum.jobis.domain.notification.spi.NotificationPort;

import java.util.List;

import static team.retum.jobis.domain.notification.persistence.entity.QNotificationEntity.notificationEntity;
import static team.retum.jobis.domain.user.persistence.entity.QUserEntity.userEntity;

@RequiredArgsConstructor
@Component
public class NotificationPersistenceAdapter implements NotificationPort {

    private final NotificationJpaRepository notificationJpaRepository;
    private final NotificationMapper notificationMapper;
    private final JPAQueryFactory queryFactory;

    @Override
    public void saveNotification(Notification notification) {
        notificationJpaRepository.save(notificationMapper.toEntity(notification));
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

    //==condition==//

    private BooleanExpression isNew(Boolean isNew) {
        return isNew == null ? null : notificationEntity.isNew.eq(isNew);
    }
}
