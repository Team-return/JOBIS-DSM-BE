package com.example.jobis.domain.company.service;

import com.example.jobis.domain.company.controller.dto.request.RegisterCompanyRequest;
import com.example.jobis.domain.company.domain.Company;
import com.example.jobis.domain.company.domain.repository.CompanyRepository;
import com.example.jobis.domain.company.facade.CompanyFacade;
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
                .companyName(companyFacade.getCompanyName(request.getBusinessNumber()))
                .businessNumber(request.getBusinessNumber())
                .password(passwordEncoder.encode(request.getPassword()))
                .build());

        //토큰 반환 추가해야함
    }
}
