package team.retum.jobis.domain.bug.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.retum.jobis.domain.bug.domain.repository.vo.QueryBugReportsVO;

import java.util.List;

@Getter
@AllArgsConstructor
public class QueryBugReportsResponse {

    private final List<QueryBugReportsVO> bugReports;
}
