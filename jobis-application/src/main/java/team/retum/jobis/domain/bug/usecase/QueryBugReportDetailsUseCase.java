package team.retum.jobis.domain.bug.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.domain.bug.dto.QueryBugReportDetailsResponse;
import team.retum.jobis.domain.bug.exception.BugReportNotFoundException;
import team.retum.jobis.domain.bug.model.BugReport;
import team.retum.jobis.domain.bug.spi.QueryBugReportPort;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class QueryBugReportDetailsUseCase {

    private final QueryBugReportPort queryBugReportPort;

    public QueryBugReportDetailsResponse execute(Long bugReportId) {
        BugReport bugReport = queryBugReportPort.queryBugReportById(bugReportId)
                .orElseThrow(() -> BugReportNotFoundException.EXCEPTION);

        return QueryBugReportDetailsResponse.from(bugReport);
    }
}
