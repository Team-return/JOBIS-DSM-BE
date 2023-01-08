package com.example.jobis.domain.company.service;

import com.example.jobis.domain.company.controller.dto.response.CompanyDetailsResponse;
import com.example.jobis.domain.company.domain.Company;
import com.example.jobis.domain.company.facade.CompanyFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class QueryCompanyDetailsService {

    private final CompanyFacade companyFacade;

    @Transactional(readOnly = true)
    public CompanyDetailsResponse execute(Long companyId) {
        Company company = companyFacade.getCompanyById(companyId);

        return CompanyDetailsResponse.builder()
                .businessNumber(company.getBusinessNumber())
                .companyProfileUrl(company.getCompanyProfileUrl())
                .companyIntroduce(company.getCompanyDetails().getCompanyIntroduce())
                .zipCode1(company.getCompanyDetails().getAddress().getZipCode1())
                .address1(company.getCompanyDetails().getAddress().getAddress1())
                .zipCode2(company.getCompanyDetails().getAddress().getZipCode2())
                .address2(company.getCompanyDetails().getAddress().getAddress2())
                .manager1(company.getCompanyDetails().getManager().getManager1())
                .phoneNumber1(company.getCompanyDetails().getManager().getPhoneNumber1())
                .manager2(company.getCompanyDetails().getManager().getManager2())
                .phoneNumber2(company.getCompanyDetails().getManager().getPhoneNumber2())
                .fax(company.getCompanyDetails().getContact().getFax())
                .email(company.getCompanyDetails().getContact().getEmail())
                .representativeName(company.getCompanyDetails().getCompanyInfo().getRepresentativeName())
                .foundedAt(company.getCompanyDetails().getCompanyInfo().getFoundedAt())
                .workerNumber(company.getCompanyDetails().getCompanyInfo().getWorkerNumber())
                .take(company.getCompanyDetails().getCompanyInfo().getTake())
                .build();
    }

}
