package team.retum.jobis.domain.company.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Builder
public class QueryCompanyDetailsResponse {

    private final String businessNumber;
    private final String companyName;
    private final String companyProfileUrl;
    private final String companyIntroduce;
    private final String mainAddress;
    private final String mainAddressDetail;
    private final String mainZipCode;
    private final String subAddress;
    private final String subAddressDetail;
    private final String subZipCode;
    private final String managerName;
    private final String managerPhoneNo;
    private final String subManagerName;
    private final String subManagerPhoneNo;
    private final String fax;
    private final String email;
    private final String representativeName;
    private final String representativePhoneNo;
    private final LocalDate foundedAt;
    private final int workerNumber;
    private final double take;
    private final Long recruitmentId;
    private final List<String> attachments;
    private final String serviceName;
    private final Long businessAreaCode;
    private final String businessArea;
    private final String bizRegistrationUrl;
}
