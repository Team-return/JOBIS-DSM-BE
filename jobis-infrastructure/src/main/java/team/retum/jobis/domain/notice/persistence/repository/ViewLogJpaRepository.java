package team.retum.jobis.domain.notice.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.retum.jobis.domain.notice.persistence.entity.ViewLogEntity;

public interface ViewLogJpaRepository extends JpaRepository<ViewLogEntity, Long> {
}
