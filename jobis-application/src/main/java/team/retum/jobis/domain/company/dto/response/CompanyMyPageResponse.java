package team.retum.jobis.domain.company.dto.response;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.domain.company.model.Company;
import team.retum.jobis.domain.company.model.CompanyType;

import java.time.LocalDate;

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
    private final String subAddress;
    private final String subAddressDetail;
    private final String subZipCode;
    private final String representative;
    private final LocalDate foundedAt;
    private final double take;
    private final int workersCount;
    private final String managerName;
    private final String managerPhoneNo;
    private final String subManagerName;
    private final String subManagerPhoneNo;
    private final String fax;
    private final String email;
    private final String companyIntroduce;
    private final String companyLogoUrl;
    private final String serviceName;
    private final String businessArea;
    private final String bizRegistrationUrl;

    public static CompanyMyPageResponse from(Company company) {
        return CompanyMyPageResponse.builder()
                .companyId(company.getId())
                .name(company.getName())
                .bizNo(company.getBizNo())
                .type(company.getType())
                .mainAddress(company.getAddressInfo().mainAddress())
                .mainAddressDetail(company.getAddressInfo().mainAddressDetail())
                .mainZipCode(company.getAddressInfo().mainZipCode())
                .subAddress(company.getAddressInfo().subAddress())
                .subAddressDetail(company.getAddressInfo().subAddressDetail())
                .subZipCode(company.getAddressInfo().subZipCode())
                .representative(company.getRepresentative())
                .foundedAt(company.getFoundedAt())
                .take(company.getTake())
                .workersCount(company.getWorkersCount())
                .managerName(company.getManagerInfo().managerName())
                .managerPhoneNo(company.getManagerInfo().managerPhoneNo())
                .subManagerName(company.getManagerInfo().subManagerName())
                .subManagerPhoneNo(company.getManagerInfo().subManagerPhoneNo())
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
