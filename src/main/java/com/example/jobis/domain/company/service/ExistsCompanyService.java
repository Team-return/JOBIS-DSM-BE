package com.example.jobis.domain.company.service;

import com.example.jobis.domain.company.controller.dto.response.ExistsCompanyResponse;
import com.example.jobis.domain.company.facade.CompanyFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ExistsCompanyService {

    private final CompanyFacade companyFacade;

    @Transactional(readOnly = true)
    public ExistsCompanyResponse execute(String businessNumber) {

        String companyName = companyFacade.getCompanyName(businessNumber);
        boolean exists = companyFacade.companyExists(businessNumber);

        return ExistsCompanyResponse.builder()
                .companyName(companyName)
                .exists(exists)
                .build();
    }
}
