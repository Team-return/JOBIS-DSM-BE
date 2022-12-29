package com.example.jobis.domain.company.controller.dto.response;

import com.example.jobis.domain.company.domain.Company;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CompanyResponse {

    private final String companyName;
    private final String companyProfileUrl;
    private final String take;

    public static CompanyResponse of(Company company) {
        return CompanyResponse.builder()
                .companyName(company.getCompanyName())
                .companyProfileUrl(company.getCompanyProfileUrl())
                .take(company.getCompanyDetails().getCompanyInfo().getTake().toString())
                .build();
    }
}
