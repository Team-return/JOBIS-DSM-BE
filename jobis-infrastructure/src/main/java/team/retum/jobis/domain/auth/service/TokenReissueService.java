package team.retum.jobis.domain.auth.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.auth.persistence.entity.RefreshTokenEntity;
import team.retum.jobis.domain.auth.persistence.repository.RefreshTokenRepository;
import team.retum.jobis.domain.auth.exception.RefreshTokenNotFoundException;
import com.example.jobisapplication.domain.user.dto.response.TokenResponse;
import com.example.jobisapplication.common.annotation.Service;
import team.retum.jobis.global.security.jwt.JwtProperties;
import team.retum.jobis.global.security.jwt.JwtTokenAdapter;
import team.retum.jobis.global.security.jwt.TokenType;

@RequiredArgsConstructor
@Service
public class TokenReissueService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtTokenAdapter jwtTokenAdapter;
    private final JwtProperties jwtProperties;

    public TokenResponse execute(String refresh) {

        RefreshTokenEntity token = refreshTokenRepository.findByToken(refresh)
                .orElseThrow(() -> RefreshTokenNotFoundException.EXCEPTION);
        String newAccess = jwtTokenAdapter.generateAccessToken(token.getId(), token.getAuthority());
        String newRefresh = jwtTokenAdapter.generateRefreshToken(token.getId(), token.getAuthority());
        token.update(newRefresh, jwtProperties.getRefreshExp().longValue());

        return TokenResponse.builder()
                .accessToken(newAccess)
                .accessExpiresAt(jwtTokenAdapter.getExpiredAt(TokenType.ACCESS))
                .refreshToken(newRefresh)
                .refreshExpiresAt(jwtTokenAdapter.getExpiredAt(TokenType.REFRESH))
                .authority(token.getAuthority())
                .build();
    }
}