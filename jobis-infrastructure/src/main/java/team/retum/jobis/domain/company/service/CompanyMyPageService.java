package team.retum.jobis.domain.company.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.company.persistence.Company;
import team.retum.jobis.domain.company.presentation.dto.response.CompanyMyPageResponse;
import team.retum.jobis.domain.persistence.facade.UserFacade;
import team.retum.jobis.global.annotation.ReadOnlyService;

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
                .take(company.getTake())
                .workersCount(company.getWorkersCount())
                .managerName(company.getManager().getManagerName())
                .managerPhoneNo(company.getManager().getManagerPhoneNo())
                .subManagerName(company.getManager().getSubManagerName())
                .subManagerPhoneNo(company.getManager().getSubManagerPhoneNo())
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
