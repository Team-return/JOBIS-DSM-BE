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
    String managerName,
    String representativePhoneNo,
    String email,
    String representativeName,
    LocalDate foundedAt,
    int workerNumber,
    double take,
    String companyProfileUrl,
    String bizRegistrationUrl,
    Long businessAreaCode,
    String serviceName,
    List<String> attachmentUrls,
    boolean headquarter
) {

}
