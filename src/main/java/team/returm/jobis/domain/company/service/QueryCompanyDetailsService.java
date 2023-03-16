package team.returm.jobis.domain.company.service;

import team.returm.jobis.domain.company.presentation.dto.response.CompanyDetailsResponse;
import team.returm.jobis.domain.company.domain.Company;
import team.returm.jobis.domain.company.facade.CompanyFacade;
import team.returm.jobis.global.annotation.ReadOnlyService;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
@ReadOnlyService
public class QueryCompanyDetailsService {

    private final CompanyFacade companyFacade;

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
