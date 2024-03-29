package team.retum.jobis.global.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.config.annotation.SecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import team.retum.jobis.global.error.GlobalExceptionFilter;
import team.retum.jobis.global.security.jwt.JwtFilter;
import team.retum.jobis.global.security.jwt.JwtParser;

@Component
@RequiredArgsConstructor
public class FilterConfig implements SecurityConfigurer<DefaultSecurityFilterChain, HttpSecurity> {

    private final JwtParser jwtParser;
    private final ObjectMapper objectMapper;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public void init(HttpSecurity http) throws Exception {
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(new JwtFilter(jwtParser), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(new GlobalExceptionFilter(objectMapper, eventPublisher), JwtFilter.class);
    }
}
