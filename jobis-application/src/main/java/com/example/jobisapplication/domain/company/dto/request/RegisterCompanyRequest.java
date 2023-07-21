package com.example.jobisapplication.domain.company.dto.request;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class RegisterCompanyRequest {

    private String name;

    private String businessNumber;

    private String password;

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

    private String representativeName;

    private LocalDate foundedAt;

    private int workerNumber;

    private double take;

    private String companyProfileUrl;

    private String bizRegistrationUrl;

    private Long businessAreaCode;

    private String serviceName;

    private List<String> attachmentUrls;
}
