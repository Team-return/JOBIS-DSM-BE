package com.example.jobis.domain.user.service;

import com.example.jobis.domain.user.controller.dto.response.TokenResponse;
import com.example.jobis.domain.user.controller.dto.request.LoginRequest;
import com.example.jobis.domain.company.domain.Company;
import com.example.jobis.domain.company.facade.CompanyFacade;
import com.example.jobis.domain.user.controller.dto.response.UserAuthResponse;
import com.example.jobis.domain.user.domain.User;
import com.example.jobis.domain.user.exception.InvalidPasswordException;
import com.example.jobis.domain.user.facade.UserFacade;
import com.example.jobis.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LoginService {

    private final UserFacade userFacade;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public UserAuthResponse execute(LoginRequest request) {

        User user = userFacade.getUser(request.getAccountId());

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw InvalidPasswordException.EXCEPTION;
        }

        String accessToken = jwtTokenProvider.generateAccessToken(user.getAccountId(), user.getAuthority());
        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getAccountId(), user.getAuthority());

        return UserAuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .accessExpiresAt(jwtTokenProvider.getExpiredAt())
                .authority(user.getAuthority())
                .build();
    }
}
