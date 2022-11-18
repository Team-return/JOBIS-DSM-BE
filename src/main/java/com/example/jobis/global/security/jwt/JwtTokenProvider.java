package com.example.jobis.global.security.jwt;

import com.example.jobis.domain.auth.domain.RefreshToken;
import com.example.jobis.domain.auth.domain.repository.RefreshTokenRepository;
import com.example.jobis.global.exception.ExpiredTokenException;
import com.example.jobis.global.exception.InvalidTokenException;
import com.example.jobis.global.security.auth.AuthDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    private final JwtProperties jwtProperties;
    private final RefreshTokenRepository refreshTokenRepository;
    private final AuthDetailsService authDetailsService;

    private static final String TOKEN_TYPE = "typ";
    private static final String ACCESS_KEY = "access";

    public String generateAccessToken(String id) {
        return generateToken(id, ACCESS_KEY);
    }

    public String generateRefreshToken(String id) {
        String token = generateToken(id, "refresh");
        refreshTokenRepository.save(
                RefreshToken.builder()
                        .token(token)
                        .ttl(jwtProperties.getRefreshExp())
                        .build()
        );
        return token;
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = authDetailsService.loadUserByUsername(getSubject(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String resolveToken(HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader(jwtProperties.getHeader());
        if(token != null && token.startsWith(jwtProperties.getPrefix())) {
            return token.replace(jwtProperties.getPrefix(), "");
        }
        return null;
    }

    public LocalDateTime getExpiredAt() {
        return LocalDateTime.now().plusSeconds(jwtProperties.getAccessExp());
    }

    private String generateToken(String id, String typ) {
        return Jwts.builder()
                .setSubject(id)
                .signWith(SignatureAlgorithm.HS256, jwtProperties.getSecret())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+jwtProperties.getAccessExp()))
                .claim(TOKEN_TYPE, typ)
                .compact();
    }

    private String getSubject(String token) {
        return getClaims(token).getSubject();
    }

    private Claims getClaims(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(jwtProperties.getSecret())
                    .parseClaimsJws(token).getBody();
        }
        catch (ExpiredJwtException e) {
            throw ExpiredTokenException.EXCEPTION;
        }
        catch (Exception e) {
            throw InvalidTokenException.EXCEPTION;
        }
    }
}
