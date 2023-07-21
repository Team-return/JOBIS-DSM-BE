package com.example.jobisapplication.domain.bug.usecase;

import com.example.jobisapplication.common.annotation.ReadOnlyUseCase;
import com.example.jobisapplication.domain.bug.dto.QueryBugReportsResponse;
import com.example.jobisapplication.domain.bug.model.DevelopmentArea;
import com.example.jobisapplication.domain.bug.spi.QueryBugReportPort;
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
