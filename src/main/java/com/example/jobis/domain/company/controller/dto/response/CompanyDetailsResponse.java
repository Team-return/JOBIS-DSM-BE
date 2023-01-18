package com.example.jobis.domain.company.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class CompanyDetailsResponse {

    private String businessNumber;
    private String companyProfileUrl;
    private String companyIntroduce;
    private String zipCode1;
    private String address1;
    private String zipCode2;
    private String address2;
    private String manager1;
    private String phoneNumber1;
    private String manager2;
    private String phoneNumber2;
    private String fax;
    private String email;
    private String representativeName;
    private LocalDate foundedAt;
    private int workerNumber;
    private int take;
}
