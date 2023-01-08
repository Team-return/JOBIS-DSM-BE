package com.example.jobis.domain.user.service;

import com.example.jobis.domain.auth.domain.RefreshToken;
import com.example.jobis.domain.auth.domain.repository.RefreshTokenRepository;
import com.example.jobis.domain.auth.exception.RefreshTokenNotFoundException;
import com.example.jobis.domain.user.controller.dto.response.TokenResponse;
import com.example.jobis.global.security.jwt.JwtProperties;
import com.example.jobis.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TokenReissueService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtProperties jwtProperties;

    @Transactional
    public TokenResponse execute(String refresh) {

        RefreshToken token = refreshTokenRepository.findByToken(refresh)
                .orElseThrow(() -> RefreshTokenNotFoundException.EXCEPTION);
        String newAccess = jwtTokenProvider.generateAccessToken(token.getId());
        String newRefresh = jwtTokenProvider.generateRefreshToken(token.getId());
        token.update(newRefresh, jwtProperties.getRefreshExp());

        return TokenResponse.builder()
                .accessToken(newAccess)
                .refreshToken(newRefresh)
                .accessExpiredAt(jwtTokenProvider.getExpiredAt())
                .build();
    }
}