package com.example.jobis.domain.company.service;

import com.example.jobis.domain.company.controller.dto.response.CheckCompanyExistsResponse;
import com.example.jobis.domain.company.facade.CompanyFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CheckCompanyExistsService {

    private final CompanyFacade companyFacade;

    @Transactional(readOnly = true)
    public CheckCompanyExistsResponse execute(String businessNumber) {
        String companyName = companyFacade.getCompanyName(businessNumber);
        boolean exists = companyFacade.companyExists(businessNumber);

        return CheckCompanyExistsResponse.builder()
                .companyName(companyName)
                .exists(exists)
                .build();
    }
}
