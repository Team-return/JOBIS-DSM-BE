package team.retum.jobis.domain.bug.dto;

import team.retum.jobis.domain.bug.spi.vo.BugReportsVO;

import java.util.List;

public record QueryBugReportsResponse(
        List<BugReportsVO> bugReports
) {}
