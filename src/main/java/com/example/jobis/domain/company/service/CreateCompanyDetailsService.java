package com.example.jobis.domain.company.service;

import com.example.jobis.domain.company.controller.dto.request.CompanyDetailsRequest;
import com.example.jobis.domain.company.domain.Company;
import com.example.jobis.domain.company.domain.CompanyDetails;
import com.example.jobis.domain.company.domain.repository.CompanyDetailsRepository;
import com.example.jobis.domain.company.domain.type.Address;
import com.example.jobis.domain.company.domain.type.CompanyInfo;
import com.example.jobis.domain.company.domain.type.Contact;
import com.example.jobis.domain.company.domain.type.Manager;
import com.example.jobis.domain.company.facade.CompanyFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateCompanyDetailsService {

    private final CompanyDetailsRepository companyDetailsRepository;
    private final CompanyFacade companyFacade;

    public void execute(CompanyDetailsRequest request) {
        Company company = companyFacade.getCompany();

        companyDetailsRepository.save(CompanyDetails.builder()
                .company(company)
                .companyIntroduce(request.getCompanyIntroduce())
                .zipCode1(request.getZipCode1())
                .address1(request.getAddress1())
                .zipCode2(request.getZipCode2())
                .address2(request.getAddress2())
                .manager1(request.getManager1())
                .phoneNumber1(request.getPhoneNumber1())
                .manager2(request.getManager2())
                .phoneNumber2(request.getPhoneNumber2())
                .fax(request.getFax())
                .email(request.getEmail())
                .representativeName(request.getRepresentativeName())
                .foundedAt(request.getFoundedAt())
                .workerNumber(request.getWorkerNumber())
                .take(request.getTake())
                .build());
    }
}
