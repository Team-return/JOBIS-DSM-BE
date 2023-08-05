package team.retum.jobis.global.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.auth.model.Authority;
import team.retum.jobis.global.exception.ExpiredTokenException;
import team.retum.jobis.global.exception.InvalidTokenException;
import team.retum.jobis.global.security.auth.company.CompanyDetailsService;
import team.retum.jobis.global.security.auth.student.StudentDetailsService;
import team.retum.jobis.global.security.auth.teacher.TeacherDetailsService;

import javax.servlet.http.HttpServletRequest;

@Component
@RequiredArgsConstructor
public class JwtParser {

    private final JwtProperties jwtProperties;
    private final CompanyDetailsService companyDetailsService;
    private final StudentDetailsService studentDetailsService;
    private final TeacherDetailsService teacherDetailsService;

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

    private UserDetails getUserDetails(String id, Authority authority) {
        return switch (authority) {
            case COMPANY -> companyDetailsService.loadUserByUsername(id);
            case STUDENT, DEVELOPER -> studentDetailsService.loadUserByUsername(id);
            case TEACHER -> teacherDetailsService.loadUserByUsername(id);
        };
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
