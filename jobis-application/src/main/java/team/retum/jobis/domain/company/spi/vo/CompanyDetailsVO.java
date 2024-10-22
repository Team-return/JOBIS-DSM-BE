package team.retum.jobis.domain.company.spi.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class CompanyDetailsVO {

    private final String businessNumber;
    private final String companyName;
    private final String companyProfileUrl;
    private final String companyIntroduce;
    private final String mainZipCode;
    private final String mainAddress;
    private final String mainAddressDetail;
    private final String subZipCode;
    private final String subAddress;
    private final String subAddressDetail;
    private final String managerName;
    private final String managerPhoneNo;
    private final String email;
    private final String representativeName;
    private final String representativePhoneNo;
    private final LocalDate foundedAt;
    private final int workerNumber;
    private final double take;
    private final Long recruitmentId;
    private final String serviceName;
    private final Long businessAreaCode;
    private final String businessArea;
    private final List<String> attachmentUrls;
    private final String bizRegistrationUrl;
    private final boolean branchExists;
}
