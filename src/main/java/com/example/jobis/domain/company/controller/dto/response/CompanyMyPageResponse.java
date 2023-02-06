package com.example.jobis.domain.company.controller.dto.response;

import com.example.jobis.domain.company.domain.enums.CompanyType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class CompanyMyPageResponse {
    private final String name;
    private final String bizNo;
    private final CompanyType type;
    private final String mainAddress;
    private final String mainZipCode;
    private final String subAddress;
    private final String subZipCode;
    private final String representative;
    private final LocalDate foundedAt;
    private final int salesPerYear;
    private final int workersCount;
    private final String managerName;
    private final String managerPhoneNo;
    private final String subManagerName;
    private final String subManagerPhoneNo;
    private final String fax;
    private final String email;
    private final String companyIntroduce;
    private final String companyLogoUrl;

}
