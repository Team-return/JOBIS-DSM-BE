package com.example.jobis.domain.company.service;

import com.example.jobis.domain.company.controller.dto.request.UpdateCompanyDetailsRequest;
import com.example.jobis.domain.company.domain.Company;
import com.example.jobis.domain.company.domain.CompanyDetails;
import com.example.jobis.domain.company.domain.repository.CompanyDetailsRepository;
import com.example.jobis.domain.company.facade.CompanyFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UpdateCompanyDetailsService {

    private final CompanyFacade companyFacade;

    @Transactional
    public void execute(UpdateCompanyDetailsRequest request) {

        Company company = companyFacade.getCompany();
        CompanyDetails companyDetails = company.getCompanyDetails();

        companyDetails.update(request);
    }
}
