package com.example.jobis.domain.company.service;

import com.example.jobis.domain.company.presentation.dto.response.CompanyMyPageResponse;
import com.example.jobis.domain.company.domain.Company;
import com.example.jobis.domain.company.domain.repository.CompanyRepository;
import com.example.jobis.domain.company.exception.CompanyNotFoundException;
import com.example.jobis.domain.user.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyMyPageService {
    private final CompanyRepository companyRepository;
    private final UserFacade userFacade;

    @Transactional(readOnly = true)
    public CompanyMyPageResponse execute() {
        UUID currentUserId = userFacade.getCurrentUserId();
        Company company = companyRepository.queryCompanyById(currentUserId)
                .orElseThrow(() -> CompanyNotFoundException.EXCEPTION);

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
