package com.example.jobis.domain.user.service;

import com.example.jobis.domain.user.controller.dto.response.TokenResponse;
import com.example.jobis.domain.user.controller.dto.request.LoginRequest;
import com.example.jobis.domain.company.domain.Company;
import com.example.jobis.domain.company.facade.CompanyFacade;
import com.example.jobis.domain.user.domain.User;
import com.example.jobis.domain.user.exception.InvalidPasswordException;
import com.example.jobis.domain.user.facade.UserFacade;
import com.example.jobis.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoginService {

    private final UserFacade userFacade;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;


    public TokenResponse execute(LoginRequest request) {

        User user = userFacade.getUser(request.getAccountId());

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw InvalidPasswordException.EXCEPTION;
        }

        String accessToken = jwtTokenProvider.generateAccessToken(user.getAccountId());
        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getAccountId());

        return TokenResponse.builder()
                .accessToken(accessToken)
                .accessExpiredAt(jwtTokenProvider.getExpiredAt())
                .refreshToken(refreshToken)
                .build();
    }
}
