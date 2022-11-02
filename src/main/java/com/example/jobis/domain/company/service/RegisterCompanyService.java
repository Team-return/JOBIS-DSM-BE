package com.example.jobis.domain.company.service;

import com.example.jobis.domain.company.controller.dto.request.RegisterCompanyRequest;
import com.example.jobis.domain.company.domain.Company;
import com.example.jobis.domain.company.domain.repository.CompanyRepository;
import com.example.jobis.domain.company.domain.type.Address;
import com.example.jobis.domain.company.domain.type.CompanyInfo;
import com.example.jobis.domain.company.domain.type.Contact;
import com.example.jobis.domain.company.domain.type.Manager;
import com.example.jobis.domain.company.exception.InvalidBusinessNumberException;
import com.example.jobis.domain.company.facade.CompanyFacade;
import com.example.jobis.infrastructure.resttemplate.facade.RestTemplateFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class RegisterCompanyService {

    private final CompanyFacade companyFacade;
    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void execute(RegisterCompanyRequest request) {

        if (!companyFacade.checkCompany(request.getBusinessNumber())) {
            throw new RuntimeException("익셉션 처리 예정");
        }

        companyRepository.save(Company.builder()
                .businessNumber(request.getBusinessNumber())
                .password(passwordEncoder.encode(request.getPassword()))
                .companyIntroduce(request.getCompanyIntroduce())
                .companyName(companyFacade.getCompanyName(request.getBusinessNumber()))
                .address(Address.builder()
                        .zipCode1(request.getZipCode1())
                        .address1(request.getAddress1())
                        .zipCode2(request.getZipCode2())
                        .address2(request.getAddress2())
                        .build())
                .manager(Manager.builder()
                        .manager1(request.getManager1())
                        .phoneNumber1(request.getPhoneNumber1())
                        .manager2(request.getManager2())
                        .phoneNumber2(request.getPhoneNumber2())
                        .build())
                .contact(Contact.builder()
                        .fax(request.getFax())
                        .email(request.getEmail())
                        .build())
                .companyInfo(CompanyInfo.builder()
                        .representativeName(request.getRepresentativeName())
                        .foundedAt(request.getFoundedAt())
                        .workerNumber(request.getWorkerNumber())
                        .take(request.getTake())
                        .build())
                .build());
    }
}
