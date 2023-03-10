package com.example.jobis.domain.company.service;

import com.example.jobis.domain.company.domain.repository.CompanyRepository;
import com.example.jobis.domain.user.controller.dto.response.TokenResponse;
import com.example.jobis.domain.company.controller.dto.request.RegisterCompanyRequest;
import com.example.jobis.domain.company.domain.Company;
import com.example.jobis.domain.company.exception.CompanyAlreadyExistsException;
import com.example.jobis.domain.company.exception.CompanyNotFoundException;
import com.example.jobis.domain.company.facade.CompanyFacade;
import com.example.jobis.domain.user.domain.User;
import com.example.jobis.domain.user.domain.enums.Authority;
import com.example.jobis.domain.user.domain.repository.UserRepository;
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
    private final UserRepository userRepository;

    @Transactional
    public TokenResponse execute(RegisterCompanyRequest request) {

        if (!companyFacade.checkCompany(request.getBusinessNumber())) {
            throw CompanyNotFoundException.EXCEPTION;
        }

        if (companyFacade.companyExists(request.getBusinessNumber())) {
            throw CompanyAlreadyExistsException.EXCEPTION;
        }

        User user = userRepository.saveUser(
                User.builder()
                        .accountId(request.getBusinessNumber())
                        .password(passwordEncoder.encode(request.getPassword()))
                        .authority(Authority.COMPANY)
                        .build()
        );

        companyRepository.saveCompany(
                Company.builder()
                        .user(user)
                        .companyIntroduce(request.getCompanyIntroduce())
                        .companyLogoUrl(request.getCompanyProfileUrl())
                        .name(request.getName())
                        .sales(request.getTake())
                        .mainAddress(request.getAddress1())
                        .mainZipCode(request.getZipCode1())
                        .subAddress(request.getAddress2())
                        .subZipCode(request.getZipCode2())
                        .managerName(request.getManager1())
                        .managerPhoneNo(request.getPhoneNumber1())
                        .subManagerName(request.getManager2())
                        .subManagerPhoneNo(request.getPhoneNumber2())
                        .workersCount(request.getWorkerNumber())
                        .email(request.getEmail())
                        .fax(request.getFax())
                        .bizNo(request.getBusinessNumber())
                        .representative(request.getRepresentativeName())
                        .foundedAt(request.getFoundedAt())
                        .build()
        );


        String accessToken = jwtTokenProvider.generateAccessToken(user.getId(), user.getAuthority());
        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getId(), user.getAuthority());

        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
