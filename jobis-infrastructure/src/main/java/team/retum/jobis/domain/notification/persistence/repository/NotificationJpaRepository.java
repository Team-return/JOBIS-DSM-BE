package team.retum.jobis.domain.notification.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import team.retum.jobis.domain.notification.persistence.entity.NotificationEntity;

public interface NotificationJpaRepository extends CrudRepository<NotificationEntity, Long> {
}
