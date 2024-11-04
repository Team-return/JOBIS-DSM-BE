package team.retum.jobis.domain.company.persistence.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.company.model.AddressInfo;
import team.retum.jobis.domain.company.model.Company;
import team.retum.jobis.domain.company.model.ManagerInfo;
import team.retum.jobis.domain.company.persistence.entity.CompanyEntity;

@RequiredArgsConstructor
@Component
public class CompanyMapper {

    public CompanyEntity toEntity(Company domain) {
        return CompanyEntity.builder()
            .id(domain.getId())
            .email(domain.getEmail())
            .bizNo(domain.getBizNo())
            .type(domain.getType())
            .companyIntroduce(domain.getCompanyIntroduce())
            .businessArea(domain.getBusinessArea())
            .companyLogoUrl(domain.getCompanyLogoUrl())
            .name(domain.getName())
            .foundedAt(domain.getFoundedAt())
            .mainAddress(domain.getAddressInfo().mainAddress())
            .mainAddressDetail(domain.getAddressInfo().mainAddressDetail())
            .mainZipCode(domain.getAddressInfo().mainZipCode())
            .bizRegistrationUrl(domain.getBizRegistrationUrl())
            .managerName(domain.getManagerInfo().managerName())
            .managerPhoneNo(domain.getManagerInfo().managerPhoneNo())
            .representative(domain.getRepresentative())
            .serviceName(domain.getServiceName())
            .take(domain.getTake())
            .isMou(domain.isMou())
            .workersCount(domain.getWorkersCount())
            .attachmentUrls(domain.getAttachmentUrls())
            .headquarter(domain.isHeadquarter())
            .build();
    }

    public Company toDomain(CompanyEntity entity) {
        return Company.builder()
            .id(entity.getId())
            .email(entity.getEmail())
            .bizNo(entity.getBizNo())
            .type(entity.getType())
            .isMou(entity.getIsMou())
            .companyIntroduce(entity.getCompanyIntroduce())
            .businessArea(entity.getBusinessArea())
            .companyLogoUrl(entity.getCompanyLogoUrl())
            .name(entity.getName())
            .foundedAt(entity.getFoundedAt())
            .addressInfo(
                AddressInfo.builder()
                    .mainAddress(entity.getAddress().getMainAddress())
                    .mainAddressDetail(entity.getAddress().getMainAddressDetail())
                    .mainZipCode(entity.getAddress().getMainZipCode())
                    .build()
            )
            .bizRegistrationUrl(entity.getBizRegistrationUrl())
            .managerInfo(
                ManagerInfo.builder()
                    .managerName(entity.getManager().getManagerName())
                    .managerPhoneNo(entity.getManager().getManagerPhoneNo())
                    .build()
            )
            .representative(entity.getRepresentative())
            .serviceName(entity.getServiceName())
            .take(entity.getTake())
            .workersCount(entity.getWorkersCount())
            .attachmentUrls(entity.getAttachmentUrls())
            .headquarter(entity.isHeadquarter())
            .build();
    }
}
