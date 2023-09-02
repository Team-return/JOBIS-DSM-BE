package team.retum.jobis.domain.notification.persistence;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.notification.model.Notification;
import team.retum.jobis.domain.notification.persistence.mapper.NotificationMapper;
import team.retum.jobis.domain.notification.persistence.repository.NotificationJpaRepository;
import team.retum.jobis.domain.notification.spi.NotificationPort;

import java.util.List;

@RequiredArgsConstructor
@Component
public class NotificationPersistenceAdapter implements NotificationPort {

    private final NotificationJpaRepository notificationJpaRepository;
    private final NotificationMapper notificationMapper;
    private final JPAQueryFactory queryFactory;

    @Override
    public void saveNotification(Notification notification) {
        notificationJpaRepository.save(
                notificationMapper.toEntity(notification)
        );
    }

    @Override
    public void saveAllNotification(List<Notification> notifications) {
        notificationJpaRepository.saveAll(
                notifications.stream().map(notificationMapper::toEntity).toList()
        );
    }
}
