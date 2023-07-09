package team.retum.jobis.domain.bug.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.retum.jobis.domain.bug.domain.repository.vo.QueryBugReportVO;

import java.util.List;

@Getter
@AllArgsConstructor
public class QueryBugReportResponse {

    private final List<QueryBugReportVO> bugReports;
}
