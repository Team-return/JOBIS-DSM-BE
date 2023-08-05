package team.retum.jobis.domain.bug.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.retum.jobis.domain.bug.spi.vo.BugReportsVO;

import java.util.List;

@Getter
@AllArgsConstructor
public class QueryBugReportsResponse {

    private final List<BugReportsVO> bugReports;
}
