package team.returm.jobis.domain.auth.service;

import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.auth.domain.RefreshToken;
import team.returm.jobis.domain.auth.domain.repository.RefreshTokenRepository;
import team.returm.jobis.domain.auth.exception.RefreshTokenNotFoundException;
import team.returm.jobis.domain.user.presentation.dto.response.TokenResponse;
import team.returm.jobis.global.annotation.Service;
import team.returm.jobis.global.security.jwt.JwtProperties;
import team.returm.jobis.global.security.jwt.JwtTokenProvider;
import team.returm.jobis.global.security.jwt.TokenType;

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