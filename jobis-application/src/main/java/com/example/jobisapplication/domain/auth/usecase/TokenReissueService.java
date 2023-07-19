package com.example.jobisapplication.domain.auth.usecase;

import com.example.jobisapplication.common.annotation.UseCase;
import com.example.jobisapplication.domain.auth.dto.TokenResponse;
import com.example.jobisapplication.domain.auth.model.RefreshToken;
import com.example.jobisapplication.domain.auth.spi.JwtPort;
import com.example.jobisapplication.domain.auth.spi.QueryRefreshTokenPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
public class TokenReissueService {

    private final QueryRefreshTokenPort queryRefreshTokenPort;
    private final JwtPort jwtPort;

    public TokenResponse execute(String refresh) {
        RefreshToken token = queryRefreshTokenPort.queryRefreshTokenByToken(refresh);
        TokenResponse newTokens = jwtPort.generateTokens(token.getId(), token.getAuthority());
        token.update(newTokens.getRefreshToken(), jwtPort.getRefreshExp());

        return newTokens;
    }
}