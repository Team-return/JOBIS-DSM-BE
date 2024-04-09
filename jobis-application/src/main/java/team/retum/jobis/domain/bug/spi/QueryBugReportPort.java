package team.retum.jobis.domain.bug.spi;

import team.retum.jobis.domain.bug.model.BugReport;
import team.retum.jobis.domain.bug.model.DevelopmentArea;
import team.retum.jobis.domain.bug.spi.vo.BugReportsVO;

import java.util.List;

public interface QueryBugReportPort {

    BugReport getByIdOrThrow(Long id);

    List<BugReportsVO> getAllByDevelopmentArea(DevelopmentArea developmentArea);
}
