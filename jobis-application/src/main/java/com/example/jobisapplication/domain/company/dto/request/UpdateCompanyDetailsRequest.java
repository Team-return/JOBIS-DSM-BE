package com.example.jobisapplication.domain.company.dto.request;

import lombok.Getter;

@Getter
public class UpdateCompanyDetailsRequest {

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

    private int workerNumber;

    private double take;

    private String companyProfileUrl;
}
