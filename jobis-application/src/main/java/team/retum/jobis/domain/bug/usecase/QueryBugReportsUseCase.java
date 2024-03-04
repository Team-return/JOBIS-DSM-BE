package team.retum.jobis.domain.bug.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.domain.bug.dto.response.QueryBugReportsResponse;
import team.retum.jobis.domain.bug.model.DevelopmentArea;
import team.retum.jobis.domain.bug.spi.QueryBugReportPort;

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
