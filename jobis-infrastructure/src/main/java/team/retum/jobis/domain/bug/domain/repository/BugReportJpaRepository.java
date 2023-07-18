package team.retum.jobis.domain.bug.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.retum.jobis.domain.bug.domain.BugReport;

public interface BugReportJpaRepository extends JpaRepository<BugReport, Long> {
}
