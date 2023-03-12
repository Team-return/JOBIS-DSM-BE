package com.example.jobis.domain.company.service;

import com.example.jobis.domain.company.presentation.dto.response.CompanyMyPageResponse;
import com.example.jobis.domain.company.domain.Company;
import com.example.jobis.domain.company.domain.repository.CompanyRepository;
import com.example.jobis.domain.company.exception.CompanyNotFoundException;
import com.example.jobis.domain.user.facade.UserFacade;
import com.example.jobis.global.annotation.ReadOnlyService;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
@ReadOnlyService
public class CompanyMyPageService {
    private final UserFacade userFacade;

    public CompanyMyPageResponse execute() {
        Company company = userFacade.getCurrentCompany();

        return CompanyMyPageResponse.builder()
                .name(company.getName())
                .bizNo(company.getBizNo())
                .type(company.getType())
                .mainAddress(company.getAddress().getMainAddress())
                .mainZipCode(company.getAddress().getMainZipCode())
                .subAddress(company.getAddress().getSubAddress())
                .subZipCode(company.getAddress().getSubZipCode())
                .representative(company.getRepresentative())
                .foundedAt(company.getFoundedAt())
                .salesPerYear(company.getSales())
                .workersCount(company.getWorkersCount())
                .managerName(company.getManager().getManagerName())
                .managerPhoneNo(company.getManager().getManagerPhoneNo())
                .subManagerName(company.getManager().getSubManagerName())
                .subManagerPhoneNo(company.getManager().getSubManagerPhoneNo())
                .fax(company.getFax())
                .email(company.getEmail())
                .companyIntroduce(company.getCompanyIntroduce())
                .companyLogoUrl(company.getCompanyLogoUrl())
                .build();
    }
}
