package com.example.jobisapplication.domain.application.usecase;

import com.example.jobisapplication.common.annotation.ReadOnlyUseCase;
import com.example.jobisapplication.domain.application.spi.QueryApplicationPort;
import com.example.jobisapplication.domain.application.spi.vo.TotalApplicationCountVO;
import lombok.RequiredArgsConstructor;
import com.example.jobisapplication.domain.application.dto.response.QueryEmploymentCountResponse;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class QueryEmploymentCountService {

    private final QueryApplicationPort queryApplicationPort;

    public QueryEmploymentCountResponse execute() {
        TotalApplicationCountVO counts = queryApplicationPort.queryTotalApplicationCount();

        return QueryEmploymentCountResponse.of(counts);
    }
}
