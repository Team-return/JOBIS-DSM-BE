package team.retum.jobis.domain.company.model;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.common.annotation.Aggregate;
import team.retum.jobis.domain.recruitment.exception.CompanyMismatchException;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder(toBuilder = true)
@Aggregate
public class Company {

    private final Long id;
    private final String name;
    private final String bizNo;
    private final CompanyType type;
    private final boolean isMou;

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
    private final String bizRegistrationUrl;
    private final String businessArea;
    private final String serviceName;
    private final List<String> attachmentUrls;

    public Company update(String mainAddress, String mainAddressDetail, String mainZipCode, String subAddress, String subAddressDetail, String subZipCode,
                          double take, int workersCount, String managerName, String managerPhoneNo, String subManagerName,
                          String subManagerPhoneNo, String companyIntroduce, String companyLogoUrl, String fax, String email, String serviceName) {
        return this.toBuilder()
                .mainAddress(mainAddress)
                .mainAddressDetail(mainAddressDetail)
                .mainZipCode(mainZipCode)
                .subAddress(subAddress)
                .subAddressDetail(subAddressDetail)
                .subZipCode(subZipCode)
                .take(take)
                .workersCount(workersCount)
                .managerName(managerName)
                .managerPhoneNo(managerPhoneNo)
                .subManagerName(subManagerName)
                .subManagerPhoneNo(subManagerPhoneNo)
                .companyIntroduce(companyIntroduce)
                .companyLogoUrl(companyLogoUrl)
                .fax(fax)
                .email(email)
                .serviceName(serviceName)
                .build();
    }

    public Company changeCompanyType(CompanyType type) {
        return this.toBuilder()
                .type(type)
                .build();
    }

    public Company convertToMou() {
        return this.toBuilder()
                .isMou(!this.isMou)
                .build();
    }

    public void verifySameCompany(Company currentCompany) {
        if (!this.getId().equals(currentCompany.getId())) {
            throw CompanyMismatchException.EXCEPTION;
        }
    }
}
