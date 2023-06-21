package team.returm.jobis.domain.bug.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.returm.jobis.domain.bug.domain.BugReport;

public interface BugReportJpaRepository extends JpaRepository<BugReport, Long> {
}
