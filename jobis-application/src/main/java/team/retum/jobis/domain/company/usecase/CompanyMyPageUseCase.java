package team.retum.jobis.domain.company.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.common.spi.SecurityPort;
import team.retum.jobis.domain.company.dto.response.CompanyMyPageResponse;
import team.retum.jobis.domain.company.exception.CompanyNotFoundException;
import team.retum.jobis.domain.company.model.Company;
import team.retum.jobis.domain.company.spi.QueryCompanyPort;

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
                .mainAddressDetail(company.getSubAddressDetail())
                .mainZipCode(company.getMainZipCode())
                .subAddress(company.getSubAddress())
                .subAddressDetail(company.getSubAddressDetail())
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
