package team.retum.jobis.domain.bug.dto.request;

import team.retum.jobis.domain.bug.spi.vo.BugReportsVO;

import java.util.List;

public record QueryBugReportsResponse(
        List<BugReportsVO> bugReports
) {}
