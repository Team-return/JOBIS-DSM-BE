package team.retum.jobis.domain.company.dto.request;

import lombok.Builder;

import java.util.List;

@Builder
public record UpdateCompanyDetailsRequest(
    String companyIntroduce,
    String mainZipCode,
    String mainAddress,
    String mainAddressDetail,
    String managerName,
    String representativePhoneNo,
    String email,
    int workerNumber,
    double take,
    String companyProfileUrl,
    String serviceName,
    List<String> attachmentUrls,
    String bizRegistrationUrl,
    boolean headquarter
) {

}
