package com.example.jobis.domain.company.service;

import com.example.jobis.domain.company.controller.dto.response.CompanyDetailsResponse;
import com.example.jobis.domain.company.domain.Company;
import com.example.jobis.domain.company.facade.CompanyFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class QueryCompanyDetailsService {

    private final CompanyFacade companyFacade;

    @Transactional(readOnly = true)
    public CompanyDetailsResponse execute(UUID companyId) {
        Company company = companyFacade.queryCompanyById(companyId);

        return CompanyDetailsResponse.builder()
                .businessNumber(company.getBizNo())
                .companyProfileUrl(company.getCompanyLogoUrl())
                .companyIntroduce(company.getCompanyIntroduce())
                .zipCode1(company.getAddress().getMainZipCode())
                .address1(company.getAddress().getMainAddress())
                .zipCode2(company.getAddress().getSubZipCode())
                .address2(company.getAddress().getSubAddress())
                .manager1(company.getManager().getManagerName())
                .phoneNumber1(company.getManager().getManagerPhoneNo())
                .manager2(company.getManager().getSubManagerName())
                .phoneNumber2(company.getManager().getSubManagerPhoneNo())
                .fax(company.getFax())
                .email(company.getEmail())
                .representativeName(company.getRepresentative())
                .foundedAt(company.getFoundedAt())
                .workerNumber(company.getWorkersCount())
                .take(company.getSales())
                .build();
    }

}
