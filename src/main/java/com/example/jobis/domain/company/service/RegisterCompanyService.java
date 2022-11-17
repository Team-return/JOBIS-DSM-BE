package com.example.jobis.domain.company.service;

import com.example.jobis.domain.company.domain.CompanyDetails;
import com.example.jobis.domain.company.domain.repository.CompanyDetailsRepository;
import com.example.jobis.domain.user.controller.dto.response.TokenResponse;
import com.example.jobis.domain.company.controller.dto.request.RegisterCompanyRequest;
import com.example.jobis.domain.company.domain.Company;
import com.example.jobis.domain.company.domain.repository.CompanyRepository;
import com.example.jobis.domain.company.exception.CompanyAlreadyExistsException;
import com.example.jobis.domain.company.exception.CompanyNotFoundException;
import com.example.jobis.domain.company.facade.CompanyFacade;
import com.example.jobis.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class RegisterCompanyService {

    private final CompanyFacade companyFacade;
    private final CompanyRepository companyRepository;
    private final CompanyDetailsRepository companyDetailsRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public TokenResponse execute(RegisterCompanyRequest request) {

        if (!companyFacade.checkCompany(request.getBusinessNumber())) {
            throw CompanyNotFoundException.EXCEPTION;
        }

        if (!companyFacade.companyExists(request.getBusinessNumber())) {
            throw CompanyAlreadyExistsException.EXCEPTION;
        }

        Company company = companyRepository.save(Company.builder()
                .companyName(companyFacade.getCompanyName(request.getBusinessNumber()))
                .accountId(request.getBusinessNumber())
                .businessNumber(request.getBusinessNumber())
                .password(passwordEncoder.encode(request.getPassword()))
                .build());

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

        String accessToken = jwtTokenProvider.generateAccessToken(company.getAccountId());
        String refreshToken = jwtTokenProvider.generateRefreshToken(company.getAccountId());

        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
