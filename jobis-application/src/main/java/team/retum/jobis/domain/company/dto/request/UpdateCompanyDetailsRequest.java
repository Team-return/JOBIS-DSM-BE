package team.retum.jobis.domain.company.dto.request;

import lombok.Builder;

import java.util.List;

@Builder
public record UpdateCompanyDetailsRequest(
        String companyIntroduce,
        String mainZipCode,
        String mainAddress,
        String mainAddressDetail,
        String subZipCode,
        String subAddress,
        String subAddressDetail,
        String managerName,
        String managerPhoneNo,
        String subManagerName,
        String subManagerPhoneNo,
        String fax,
        String email,
        int workerNumber,
        double take,
        String companyProfileUrl,
        String serviceName,
        String representativePhoneNo,
        Long businessAreaCode,
        String bizRegistrationUrl,
        List<String> attachmentUrls
) {
}
