package team.retum.jobis.domain.auth.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.auth.persistence.RefreshToken;
import team.retum.jobis.domain.auth.persistence.repository.RefreshTokenRepository;
import team.retum.jobis.domain.auth.exception.RefreshTokenNotFoundException;
import team.retum.jobis.domain.persistence.presentation.dto.response.TokenResponse;
import team.retum.jobis.global.annotation.Service;
import team.retum.jobis.global.security.jwt.JwtProperties;
import team.retum.jobis.global.security.jwt.JwtTokenProvider;
import team.retum.jobis.global.security.jwt.TokenType;

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
        token.update(newRefresh, jwtProperties.getRefreshExp().longValue());

        return TokenResponse.builder()
                .accessToken(newAccess)
                .accessExpiresAt(jwtTokenProvider.getExpiredAt(TokenType.ACCESS))
                .refreshToken(newRefresh)
                .refreshExpiresAt(jwtTokenProvider.getExpiredAt(TokenType.REFRESH))
                .authority(token.getAuthority())
                .build();
    }
}