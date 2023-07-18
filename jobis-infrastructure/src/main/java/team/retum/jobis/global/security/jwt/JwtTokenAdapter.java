package team.retum.jobis.global.security.jwt;

import com.example.jobisapplication.domain.auth.dto.TokenResponse;
import com.example.jobisapplication.domain.acceptance.spi.JwtPort;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.auth.persistence.entity.RefreshTokenEntity;
import team.retum.jobis.domain.auth.persistence.repository.RefreshTokenRepository;
import com.example.jobisapplication.domain.auth.model.Authority;

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

    public String generateRefreshToken(Long userId, Authority authority) {
        String token = generateToken(userId.toString(), TokenType.REFRESH, jwtProperties.getRefreshExp(), authority);
        refreshTokenRepository.save(
                RefreshTokenEntity.builder()
                        .id(userId)
                        .token(token)
                        .authority(authority)
                        .ttl(jwtProperties.getRefreshExp().longValue())
                        .build()
        );
        return token;
    }

    public LocalDateTime getExpiredAt(TokenType type) {
        return switch (type) {
            case ACCESS -> LocalDateTime.now().plusSeconds(jwtProperties.getAccessExp());
            case REFRESH -> LocalDateTime.now().plusSeconds(jwtProperties.getRefreshExp());
        };
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
    public TokenResponse generateTokens(Long userId, Authority authority) {
        String access = generateAccessToken(userId, authority);
        String refresh = generateRefreshToken(userId, authority);

        return new TokenResponse(
                access,
                LocalDateTime.now().plusSeconds(jwtProperties.getAccessExp()),
                refresh,
                LocalDateTime.now().plusSeconds(jwtProperties.getRefreshExp())
        );
    }
}
