package team.retum.jobis.domain.bug.service;

import lombok.RequiredArgsConstructor;
import com.example.jobisapplication.domain.bug.model.DevelopmentArea;
import team.retum.jobis.domain.bug.persistence.repository.BugReportRepository;
import com.example.jobisapplication.domain.bug.dto.QueryBugReportsResponse;
import com.example.jobisapplication.common.annotation.ReadOnlyService;

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
