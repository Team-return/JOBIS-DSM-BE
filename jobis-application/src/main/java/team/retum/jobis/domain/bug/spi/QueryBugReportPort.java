package team.retum.jobis.domain.bug.spi;

import team.retum.jobis.domain.bug.model.BugReport;
import team.retum.jobis.domain.bug.model.DevelopmentArea;
import team.retum.jobis.domain.bug.spi.vo.BugReportsVO;

import java.util.List;
import java.util.Optional;

public interface QueryBugReportPort {

    Optional<BugReport> queryBugReportById(Long id);

    List<BugReportsVO> queryBugReportsByDevelopmentArea(DevelopmentArea developmentArea);
}
