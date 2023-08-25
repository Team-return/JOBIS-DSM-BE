package team.retum.jobis.domain.notification.persistence;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.notification.persistence.repository.NotificationJpaRepository;

@RequiredArgsConstructor
@Component
public class NotificationPersistenceAdapter {

    private final NotificationJpaRepository notificationJpaRepository;
    private final JPAQueryFactory queryFactory;
}
