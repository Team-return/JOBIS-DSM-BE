package team.retum.jobis.domain.bug.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.retum.jobis.domain.bug.persistence.BugReport;

public interface BugReportJpaRepository extends JpaRepository<BugReport, Long> {
}
