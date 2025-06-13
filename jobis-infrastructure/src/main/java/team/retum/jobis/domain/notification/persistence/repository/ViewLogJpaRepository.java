package team.retum.jobis.domain.notification.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.retum.jobis.domain.notification.persistence.entity.ViewLogEntity;

public interface ViewLogJpaRepository extends JpaRepository<ViewLogEntity, Long> {
    boolean existsByPostIdAndUserId(Long postId, Long userId);
}
