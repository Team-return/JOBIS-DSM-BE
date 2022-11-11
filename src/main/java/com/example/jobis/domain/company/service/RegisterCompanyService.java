package com.example.jobis.domain.company.service;

import com.example.jobis.domain.auth.controller.dto.response.TokenResponse;
import com.example.jobis.domain.company.controller.dto.request.RegisterCompanyRequest;
import com.example.jobis.domain.company.domain.Company;
import com.example.jobis.domain.company.domain.repository.CompanyRepository;
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
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public TokenResponse execute(RegisterCompanyRequest request) {

        if (!companyFacade.checkCompany(request.getBusinessNumber())) {
            throw CompanyNotFoundException.EXCEPTION;
        }

        if (!companyFacade.companyExists(request.getBusinessNumber())) {
            TokenResponse token = signup(request);

            return TokenResponse.builder()
                    .accessToken(token.getAccessToken())
                    .refreshToken(token.getRefreshToken())
                    .build();
        } else {
            TokenResponse token = login(request);

            return TokenResponse.builder()
                    .accessToken(token.getAccessToken())
                    .refreshToken(token.getRefreshToken())
                    .build();
        }
    }

    private TokenResponse signup(RegisterCompanyRequest request) {

        Company company = companyRepository.save(Company.builder()
                .companyName(companyFacade.getCompanyName(request.getBusinessNumber()))
                .accountId(request.getBusinessNumber())
                .businessNumber(request.getBusinessNumber())
                .password(passwordEncoder.encode(request.getPassword()))
                .build());

        String accessToken = jwtTokenProvider.generateAccessToken(company.getAccountId());
        String refreshToken = jwtTokenProvider.generateRefreshToken(company.getAccountId());

        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    private TokenResponse login(RegisterCompanyRequest request) {
        Company company = companyFacade.getCompanyByBusinessNumber(request.getBusinessNumber());

        String accessToken = jwtTokenProvider.generateAccessToken(company.getAccountId());
        String refreshToken = jwtTokenProvider.generateRefreshToken(company.getAccountId());

        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
