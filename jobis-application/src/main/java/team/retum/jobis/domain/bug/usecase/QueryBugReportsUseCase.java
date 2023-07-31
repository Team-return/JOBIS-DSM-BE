package team.retum.jobis.domain.bug.usecase;

import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.domain.bug.dto.QueryBugReportsResponse;
import team.retum.jobis.domain.bug.model.DevelopmentArea;
import team.retum.jobis.domain.bug.spi.QueryBugReportPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class QueryBugReportsUseCase {

    private final QueryBugReportPort queryBugReportPort;

    public QueryBugReportsResponse execute(DevelopmentArea developmentArea) {
        return new QueryBugReportsResponse(
                queryBugReportPort.queryBugReportsByDevelopmentArea(developmentArea)
        );
    }
}
