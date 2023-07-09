package team.retum.jobis.domain.bug.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.bug.domain.repository.BugRepository;
import team.retum.jobis.domain.bug.presentation.dto.response.QueryBugReportResponse;
import team.retum.jobis.global.annotation.ReadOnlyService;

@RequiredArgsConstructor
@ReadOnlyService
public class QueryBugReportService {

    private final BugRepository bugRepository;

    public QueryBugReportResponse execute() {
        return new QueryBugReportResponse(
                bugRepository.queryBugReports()
        );
    }
}
