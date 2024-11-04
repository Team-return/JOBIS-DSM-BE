package team.retum.jobis.domain.company.dto.response;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.domain.company.model.Company;
import team.retum.jobis.domain.company.model.CompanyType;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class CompanyMyPageResponse {

    private final Long companyId;
    private final String name;
    private final String bizNo;
    private final CompanyType type;
    private final String mainAddress;
    private final String mainAddressDetail;
    private final String mainZipCode;
    private final String representative;
    private final LocalDate foundedAt;
    private final double take;
    private final int workersCount;
    private final String managerName;
    private final String managerPhoneNo;
    private final String email;
    private final String companyIntroduce;
    private final String companyLogoUrl;
    private final String serviceName;
    private final String businessArea;
    private final String bizRegistrationUrl;
    private final List<String> attachmentUrls;
    private final boolean headquarter;

    public static CompanyMyPageResponse from(Company company) {
        return CompanyMyPageResponse.builder()
            .companyId(company.getId())
            .name(company.getName())
            .bizNo(company.getBizNo())
            .type(company.getType())
            .mainAddress(company.getAddressInfo().mainAddress())
            .mainAddressDetail(company.getAddressInfo().mainAddressDetail())
            .mainZipCode(company.getAddressInfo().mainZipCode())
            .representative(company.getRepresentative())
            .foundedAt(company.getFoundedAt())
            .take(company.getTake())
            .workersCount(company.getWorkersCount())
            .managerName(company.getManagerInfo().managerName())
            .managerPhoneNo(company.getManagerInfo().managerPhoneNo())
            .email(company.getEmail())
            .companyIntroduce(company.getCompanyIntroduce())
            .companyLogoUrl(company.getCompanyLogoUrl())
            .serviceName(company.getServiceName())
            .businessArea(company.getBusinessArea())
            .bizRegistrationUrl(company.getBizRegistrationUrl())
            .attachmentUrls(company.getAttachmentUrls())
            .headquarter(company.isHeadquarter())
            .build();
    }
}
