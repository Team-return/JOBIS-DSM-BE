package com.example.jobisapplication.domain.company.usecase;

import com.example.jobisapplication.common.annotation.ReadOnlyUseCase;
import lombok.RequiredArgsConstructor;
import com.example.jobisapplication.domain.company.dto.response.CompanyMyPageResponse;
import team.retum.jobis.domain.user.facade.UserFacade;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class CompanyMyPageUseCase {

    private final UserFacade userFacade;

    public CompanyMyPageResponse execute() {
        CompanyEntity companyEntity = userFacade.getCurrentCompany();

        return CompanyMyPageResponse.builder()
                .name(companyEntity.getName())
                .bizNo(companyEntity.getBizNo())
                .type(companyEntity.getType())
                .mainAddress(companyEntity.getAddress().getMainAddress())
                .mainZipCode(companyEntity.getAddress().getMainZipCode())
                .subAddress(companyEntity.getAddress().getSubAddress())
                .subZipCode(companyEntity.getAddress().getSubZipCode())
                .representative(companyEntity.getRepresentative())
                .foundedAt(companyEntity.getFoundedAt())
                .take(companyEntity.getTake())
                .workersCount(companyEntity.getWorkersCount())
                .managerName(companyEntity.getManager().getManagerName())
                .managerPhoneNo(companyEntity.getManager().getManagerPhoneNo())
                .subManagerName(companyEntity.getManager().getSubManagerName())
                .subManagerPhoneNo(companyEntity.getManager().getSubManagerPhoneNo())
                .fax(companyEntity.getFax())
                .email(companyEntity.getEmail())
                .companyIntroduce(companyEntity.getCompanyIntroduce())
                .companyLogoUrl(companyEntity.getCompanyLogoUrl())
                .serviceName(companyEntity.getServiceName())
                .businessArea(companyEntity.getBusinessArea())
                .bizRegistrationUrl(companyEntity.getBizRegistrationUrl())
                .build();
    }
}
