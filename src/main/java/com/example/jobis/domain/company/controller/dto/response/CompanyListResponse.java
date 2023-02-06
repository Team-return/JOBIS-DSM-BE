package com.example.jobis.domain.company.controller.dto.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class CompanyListResponse {

    private final String companyName;
    private final String companyProfileUrl;
    private final int take;

    @Builder
    @QueryProjection
    public CompanyListResponse(String companyName, String companyProfileUrl, int take) {
        this.companyName = companyName;
        this.companyProfileUrl = companyProfileUrl;
        this.take = take;
    }
}
