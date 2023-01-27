package com.example.jobis.domain.company.controller.dto.response;

import com.example.jobis.domain.company.domain.Company;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CompanyResponse {

    private final String companyName;
    private final String companyProfileUrl;
    private final int take;

    @Builder
    @QueryProjection
    public CompanyResponse(String companyName, String companyProfileUrl, int take) {
        this.companyName = companyName;
        this.companyProfileUrl = companyProfileUrl;
        this.take = take;
    }

    public static CompanyResponse of(Company company) {
        return CompanyResponse.builder()
                .companyName(company.getName())
                .companyProfileUrl(company.getCompanyLogoUrl())
                .take(company.getSales())
                .build();
    }
}
