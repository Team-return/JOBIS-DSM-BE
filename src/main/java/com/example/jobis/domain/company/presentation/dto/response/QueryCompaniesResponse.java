package com.example.jobis.domain.company.presentation.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;

@Getter
public class QueryCompaniesResponse {

    private final String companyName;
    private final String companyProfileUrl;
    private final int take;

    @Builder
    @QueryProjection
    public QueryCompaniesResponse(String companyName, String companyProfileUrl, int take) {
        this.companyName = companyName;
        this.companyProfileUrl = companyProfileUrl;
        this.take = take;
    }
}
