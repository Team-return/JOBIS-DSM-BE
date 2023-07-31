package team.retum.jobis.domain.bug.dto;

import team.retum.jobis.domain.bug.spi.vo.BugReportsVO;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class QueryBugReportsResponse {

    private final List<BugReportsVO> bugReports;
}
