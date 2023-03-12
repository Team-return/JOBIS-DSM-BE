package com.example.jobis.domain.company.service;

import com.example.jobis.domain.company.presentation.dto.response.CheckCompanyExistsResponse;
import com.example.jobis.domain.company.facade.CompanyFacade;
import com.example.jobis.global.annotation.ReadOnlyService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ReadOnlyService
public class CheckCompanyExistsService {

    private final CompanyFacade companyFacade;

    public CheckCompanyExistsResponse execute(String businessNumber) {
        String companyName = companyFacade.getCompanyName(businessNumber);
        boolean exists = companyFacade.companyExists(businessNumber);

        return CheckCompanyExistsResponse.builder()
                .companyName(companyName)
                .exists(exists)
                .build();
    }
}
