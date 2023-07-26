package com.example.jobisapplication.domain.company.usecase;

import com.example.jobisapplication.common.annotation.ReadOnlyUseCase;
import com.example.jobisapplication.common.spi.SecurityPort;
import com.example.jobisapplication.domain.company.exception.CompanyNotFoundException;
import com.example.jobisapplication.domain.company.model.Company;
import com.example.jobisapplication.domain.company.spi.QueryCompanyPort;
import lombok.RequiredArgsConstructor;
import com.example.jobisapplication.domain.company.dto.response.CompanyMyPageResponse;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class CompanyMyPageUseCase {

    private final QueryCompanyPort queryCompanyPort;
    private final SecurityPort securityPort;

    public CompanyMyPageResponse execute() {
        Long currentUserId = securityPort.getCurrentUserId();
        Company company = queryCompanyPort.queryCompanyById(currentUserId)
                .orElseThrow(() -> CompanyNotFoundException.EXCEPTION);

        return CompanyMyPageResponse.builder()
                .name(company.getName())
                .bizNo(company.getBizNo())
                .type(company.getType())
                .mainAddress(company.getMainAddress())
                .mainZipCode(company.getMainZipCode())
                .subAddress(company.getSubAddress())
                .subZipCode(company.getSubZipCode())
                .representative(company.getRepresentative())
                .foundedAt(company.getFoundedAt())
                .take(company.getTake())
                .workersCount(company.getWorkersCount())
                .managerName(company.getManagerName())
                .managerPhoneNo(company.getManagerPhoneNo())
                .subManagerName(company.getSubManagerName())
                .subManagerPhoneNo(company.getSubManagerPhoneNo())
                .fax(company.getFax())
                .email(company.getEmail())
                .companyIntroduce(company.getCompanyIntroduce())
                .companyLogoUrl(company.getCompanyLogoUrl())
                .serviceName(company.getServiceName())
                .businessArea(company.getBusinessArea())
                .bizRegistrationUrl(company.getBizRegistrationUrl())
                .build();
    }
}
