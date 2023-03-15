package com.example.jobis.domain.user.service;

import com.example.jobis.domain.auth.domain.RefreshToken;
import com.example.jobis.domain.auth.domain.repository.RefreshTokenRepository;
import com.example.jobis.domain.auth.exception.RefreshTokenNotFoundException;
import com.example.jobis.domain.user.presentation.dto.response.TokenResponse;
import com.example.jobis.global.annotation.Service;
import com.example.jobis.global.security.jwt.JwtProperties;
import com.example.jobis.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TokenReissueService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtProperties jwtProperties;

    public TokenResponse execute(String refresh) {

        RefreshToken token = refreshTokenRepository.findByToken(refresh)
                .orElseThrow(() -> RefreshTokenNotFoundException.EXCEPTION);
        String newAccess = jwtTokenProvider.generateAccessToken(token.getId(), token.getAuthority());
        String newRefresh = jwtTokenProvider.generateRefreshToken(token.getId(), token.getAuthority());
        token.update(newRefresh, jwtProperties.getRefreshExp());

        return TokenResponse.builder()
                .accessToken(newAccess)
                .refreshToken(newRefresh)
                .accessExpiredAt(jwtTokenProvider.getExpiredAt())
                .build();
    }
}