package team.returm.jobis.global.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import team.returm.jobis.domain.auth.domain.RefreshToken;
import team.returm.jobis.domain.auth.domain.repository.RefreshTokenRepository;
import team.returm.jobis.domain.user.domain.enums.Authority;
import team.returm.jobis.global.exception.ExpiredTokenException;
import team.returm.jobis.global.exception.InvalidTokenException;
import team.returm.jobis.global.security.auth.company.CompanyDetailsService;
import team.returm.jobis.global.security.auth.student.StudentDetailsService;
import team.returm.jobis.global.security.auth.teacher.TeacherDetailsService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final JwtProperties jwtProperties;
    private final RefreshTokenRepository refreshTokenRepository;
    private final CompanyDetailsService companyDetailsService;
    private final StudentDetailsService studentDetailsService;
    private final TeacherDetailsService teacherDetailsService;

    public String generateAccessToken(Long userId, Authority authority) {
        return generateToken(userId.toString(), TokenType.ACCESS, jwtProperties.getAccessExp(), authority);
    }

    public String generateRefreshToken(Long userId, Authority authority) {
        String token = generateToken(userId.toString(), TokenType.REFRESH, jwtProperties.getRefreshExp(), authority);
        refreshTokenRepository.save(
                RefreshToken.builder()
                        .id(userId)
                        .token(token)
                        .authority(authority)
                        .ttl(jwtProperties.getRefreshExp().longValue())
                        .build()
        );
        return token;
    }

    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);

        if (!claims.get("type").equals(TokenType.ACCESS.name())) {
            throw InvalidTokenException.EXCEPTION;
        }

        Authority authority = Authority.valueOf(claims.get("authority", String.class));
        UserDetails userDetails = getUserDetails(claims.getSubject(), authority);
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String resolveToken(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader(jwtProperties.getHeader());
        if (token != null && token.startsWith(jwtProperties.getPrefix())) {
            return token.replace(jwtProperties.getPrefix(), "");
        }
        return null;
    }

    public LocalDateTime getExpiredAt(TokenType type) {
        return switch (type) {
            case ACCESS -> LocalDateTime.now().plusSeconds(jwtProperties.getAccessExp());
            case REFRESH -> LocalDateTime.now().plusSeconds(jwtProperties.getRefreshExp());
        };
    }

    private UserDetails getUserDetails(String id, Authority authority) {
        return switch (authority) {
            case COMPANY -> companyDetailsService.loadUserByUsername(id);
            case STUDENT, DEVELOPER -> studentDetailsService.loadUserByUsername(id);
            case TEACHER -> teacherDetailsService.loadUserByUsername(id);
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

    private Claims getClaims(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(jwtProperties.getSecret())
                    .parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            throw ExpiredTokenException.EXCEPTION;
        } catch (Exception e) {
            throw InvalidTokenException.EXCEPTION;
        }
    }
}
