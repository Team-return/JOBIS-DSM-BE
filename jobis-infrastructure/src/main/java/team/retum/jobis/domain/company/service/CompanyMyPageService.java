package team.retum.jobis.domain.company.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.company.persistence.entity.CompanyEntity;
import team.retum.jobis.domain.company.presentation.dto.response.CompanyMyPageResponse;
import team.retum.jobis.domain.user.facade.UserFacade;
import com.example.jobisapplication.common.annotation.ReadOnlyService;

@RequiredArgsConstructor
@ReadOnlyService
public class CompanyMyPageService {

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
