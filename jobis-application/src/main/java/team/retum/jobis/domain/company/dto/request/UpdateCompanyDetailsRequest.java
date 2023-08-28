package team.retum.jobis.domain.company.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateCompanyDetailsRequest {

    private String companyIntroduce;

    private String mainZipCode;

    private String mainAddress;

    private String mainAddressDetail;

    private String subZipCode;

    private String subAddress;

    private String subAddressDetail;

    private String managerName;

    private String managerPhoneNo;

    private String subManagerName;

    private String subManagerPhoneNo;

    private String fax;

    private String email;

    private int workerNumber;

    private double take;

    private String companyProfileUrl;

    private String serviceName;
}
