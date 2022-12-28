package com.example.jobis.domain.company.service;

import com.example.jobis.domain.company.controller.dto.request.ExistsCompanyRequest;
import com.example.jobis.domain.company.controller.dto.response.ExistsCompanyResponse;
import com.example.jobis.domain.company.domain.Company;
import com.example.jobis.domain.company.facade.CompanyFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ExistsCompanyService {

    private final CompanyFacade companyFacade;

    public ExistsCompanyResponse execute(ExistsCompanyRequest request) {

        String companyName = companyFacade.getCompanyName(request.getBusinessNumber());
        boolean exists = companyFacade.companyExists(request.getBusinessNumber());

        return ExistsCompanyResponse.builder()
                .companyName(companyName)
                .exists(exists)
                .build();
    }
}
