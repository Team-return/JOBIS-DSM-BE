package team.retum.jobis.domain.company.persistence.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.company.model.Company;
import team.retum.jobis.domain.company.persistence.entity.CompanyEntity;

@RequiredArgsConstructor
@Component
public class CompanyMapper {

    public CompanyEntity toEntity(Company domain) {
        return CompanyEntity.builder()
                .id(domain.getId())
                .fax(domain.getFax())
                .email(domain.getEmail())
                .bizNo(domain.getBizNo())
                .type(domain.getType())
                .companyIntroduce(domain.getCompanyIntroduce())
                .businessArea(domain.getBusinessArea())
                .companyLogoUrl(domain.getCompanyLogoUrl())
                .name(domain.getName())
                .foundedAt(domain.getFoundedAt())
                .mainAddress(domain.getMainAddress())
                .mainAddressDetail(domain.getMainAddressDetail())
                .mainZipCode(domain.getMainZipCode())
                .subAddress(domain.getSubAddress())
                .subAddressDetail(domain.getSubAddressDetail())
                .subZipCode(domain.getSubZipCode())
                .bizRegistrationUrl(domain.getBizRegistrationUrl())
                .managerName(domain.getManagerName())
                .managerPhoneNo(domain.getManagerPhoneNo())
                .subManagerName(domain.getSubManagerName())
                .subManagerPhoneNo(domain.getSubManagerPhoneNo())
                .representative(domain.getRepresentative())
                .serviceName(domain.getServiceName())
                .take(domain.getTake())
                .isMou(domain.isMou())
                .workersCount(domain.getWorkersCount())
                .attachmentUrls(domain.getAttachmentUrls())
                .build();
    }

    public Company toDomain(CompanyEntity entity) {
        return Company.builder()
                .id(entity.getId())
                .fax(entity.getFax())
                .email(entity.getEmail())
                .bizNo(entity.getBizNo())
                .type(entity.getType())
                .isMou(entity.isMou())
                .companyIntroduce(entity.getCompanyIntroduce())
                .businessArea(entity.getBusinessArea())
                .companyLogoUrl(entity.getCompanyLogoUrl())
                .name(entity.getName())
                .foundedAt(entity.getFoundedAt())
                .mainAddress(entity.getAddress().getMainAddress())
                .mainAddressDetail(entity.getAddress().getMainAddressDetail())
                .mainZipCode(entity.getAddress().getMainZipCode())
                .subAddress(entity.getAddress().getSubAddress())
                .subAddressDetail(entity.getAddress().getSubAddressDetail())
                .subZipCode(entity.getAddress().getSubZipCode())
                .bizRegistrationUrl(entity.getBizRegistrationUrl())
                .managerName(entity.getManager().getManagerName())
                .managerPhoneNo(entity.getManager().getManagerPhoneNo())
                .subManagerName(entity.getManager().getSubManagerName())
                .subManagerPhoneNo(entity.getManager().getSubManagerPhoneNo())
                .representative(entity.getRepresentative())
                .serviceName(entity.getServiceName())
                .take(entity.getTake())
                .workersCount(entity.getWorkersCount())
                .attachmentUrls(entity.getAttachmentUrls())
                .build();
    }
}
