package team.retum.jobis.global.security.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.auth.dto.response.TokenResponse;
import team.retum.jobis.domain.auth.model.Authority;
import team.retum.jobis.domain.auth.model.PlatformType;
import team.retum.jobis.domain.auth.persistence.entity.RefreshTokenEntity;
import team.retum.jobis.domain.auth.persistence.repository.RefreshTokenRepository;
import team.retum.jobis.domain.auth.spi.JwtPort;

import java.time.LocalDateTime;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenAdapter implements JwtPort {

    private final JwtProperties jwtProperties;
    private final RefreshTokenRepository refreshTokenRepository;

    public String generateAccessToken(Long userId, Authority authority) {
        return generateToken(userId.toString(), TokenType.ACCESS, jwtProperties.getAccessExp(), authority);
    }

    public String generateRefreshToken(Long userId, Authority authority, PlatformType platformType) {
        String token = generateToken(userId.toString(), TokenType.REFRESH, jwtProperties.getRefreshExp(), authority);

        RefreshTokenEntity refreshToken = refreshTokenRepository.findByUserIdAndPlatformType(userId, platformType)
            .orElse(
                RefreshTokenEntity.builder()
                    .token(token)
                    .userId(userId)
                    .authority(authority)
                    .platformType(platformType)
                    .ttl(jwtProperties.getRefreshExp().longValue())
                    .build()
            );
        refreshTokenRepository.save(refreshToken.updateToken(token));
        return token;
    }

    public LocalDateTime getExpiredAt(TokenType type) {
        return switch (type) {
            case ACCESS -> LocalDateTime.now().plusSeconds(jwtProperties.getAccessExp());
            case REFRESH -> LocalDateTime.now().plusSeconds(jwtProperties.getRefreshExp());
        };
    }

    @Override
    public Long getRefreshExp() {
        return jwtProperties.getRefreshExp().longValue();
    }

    private String generateToken(String id, TokenType type, Integer exp, Authority authority) {
        return Jwts.builder()
            .setSubject(id)
            .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecret())
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + (exp * 1000)))
            .claim("type", type.name())
            .claim("authority", authority)
            .compact();
    }

    @Override
    public TokenResponse generateTokens(Long userId, Authority authority, PlatformType platformType) {
        String access = generateAccessToken(userId, authority);
        String refresh = generateRefreshToken(userId, authority, platformType);

        return TokenResponse.builder()
            .accessToken(access)
            .accessExpiresAt(LocalDateTime.now().plusSeconds(jwtProperties.getAccessExp()))
            .refreshToken(refresh)
            .refreshExpiresAt(LocalDateTime.now().plusSeconds(jwtProperties.getRefreshExp()))
            .authority(authority)
            .platformType(platformType)
            .build();
    }
}
