package team.retum.jobis.domain.bug.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.retum.jobis.domain.bug.persistence.entity.BugReportEntity;

public interface BugReportJpaRepository extends JpaRepository<BugReportEntity, Long> {
}
