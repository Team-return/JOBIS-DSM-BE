package team.returm.jobis.domain.user.service;

import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.auth.domain.RefreshToken;
import team.returm.jobis.domain.auth.domain.repository.RefreshTokenRepository;
import team.returm.jobis.domain.auth.exception.RefreshTokenNotFoundException;
import team.returm.jobis.domain.user.presentation.dto.response.TokenResponse;
import team.returm.jobis.global.annotation.Service;
import team.returm.jobis.global.security.jwt.JwtProperties;
import team.returm.jobis.global.security.jwt.JwtTokenProvider;

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