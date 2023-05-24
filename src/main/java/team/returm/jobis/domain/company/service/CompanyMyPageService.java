package team.returm.jobis.domain.company.service;

import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.company.domain.Company;
import team.returm.jobis.domain.company.presentation.dto.response.CompanyMyPageResponse;
import team.returm.jobis.domain.user.facade.UserFacade;
import team.returm.jobis.global.annotation.ReadOnlyService;

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
                .salesPerYear(company.getTake())
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
