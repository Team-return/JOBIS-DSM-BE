package com.example.jobisapplication.domain.company.model;

import com.example.jobisapplication.common.annotation.Aggregate;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

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
    private final String bizRegistrationUrl;
    private final String businessArea;
    private final String serviceName;

    public Company update(String mainAddress, String mainZipCode, String subAddress, String subZipCode,
                       double take, int workersCount, String managerName, String managerPhoneNo, String subManagerName,
                       String subManagerPhoneNo, String companyIntroduce, String companyLogoUrl, String fax, String email) {
        return this.toBuilder()
                .mainAddress(mainAddress)
                .mainZipCode(mainZipCode)
                .subAddress(subAddress)
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

}
