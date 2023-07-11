package team.retum.jobis.domain.bug.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.bug.domain.enums.DevelopmentArea;
import team.retum.jobis.domain.bug.domain.repository.BugReportRepository;
import team.retum.jobis.domain.bug.presentation.dto.response.QueryBugReportsResponse;
import team.retum.jobis.global.annotation.ReadOnlyService;

@RequiredArgsConstructor
@ReadOnlyService
public class QueryBugReportsService {

    private final BugReportRepository bugReportRepository;

    public QueryBugReportsResponse execute(DevelopmentArea developmentArea) {
        return new QueryBugReportsResponse(
                bugReportRepository.queryBugReportsByConditions(developmentArea)
        );
    }
}
