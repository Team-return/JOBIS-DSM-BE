package team.retum.jobis.domain.notification.persistence;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.notification.persistence.repository.NotificationJpaRepository;
import team.retum.jobis.domain.notification.spi.NotificationPort;

@RequiredArgsConstructor
@Component
public class NotificationPersistenceAdapter implements NotificationPort {

    private final NotificationJpaRepository notificationJpaRepository;
    private final JPAQueryFactory queryFactory;
}
