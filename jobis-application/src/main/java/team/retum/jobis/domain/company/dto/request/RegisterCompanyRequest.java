package team.retum.jobis.domain.company.dto.request;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record RegisterCompanyRequest(
        String name,
        String businessNumber,
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
        String representativeName,
        String representativePhoneNo,
        LocalDate foundedAt,
        int workerNumber,
        double take,
        String companyProfileUrl,
        String bizRegistrationUrl,
        Long businessAreaCode,
        String serviceName,
        List<String> attachmentUrls
) {
}
