package team.retum.jobis.domain.company.dto.request;

import lombok.Builder;

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
        String serviceName
) {
}
