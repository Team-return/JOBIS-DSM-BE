package com.example.jobisapplication.domain.application.usecase;

import com.example.jobisapplication.common.annotation.ReadOnlyUseCase;
import com.example.jobisapplication.domain.application.spi.QueryApplicationPort;
import lombok.RequiredArgsConstructor;
import com.example.jobisapplication.domain.application.dto.response.QueryPassedApplicationStudentsResponse;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class QueryPassedApplicationStudentsService {

    private final QueryApplicationPort queryApplicationPort;

    public QueryPassedApplicationStudentsResponse execute(Long companyId) {
        return new QueryPassedApplicationStudentsResponse(
                queryApplicationPort.queryPassedApplicationStudentsByCompanyId(companyId).stream()
                        .map(QueryPassedApplicationStudentsResponse::of)
                        .toList()
        );
    }
}
