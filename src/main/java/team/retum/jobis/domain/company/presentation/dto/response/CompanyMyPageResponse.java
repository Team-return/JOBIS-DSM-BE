package team.retum.jobis.domain.company.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.domain.company.domain.enums.CompanyType;

import java.time.LocalDate;

@Getter
@Builder
public class CompanyMyPageResponse {
    private final String name;
    private final String bizNo;
    private final CompanyType type;
    private final String mainAddress;
    private final String mainZipCode;
    private final String subAddress;
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
}
