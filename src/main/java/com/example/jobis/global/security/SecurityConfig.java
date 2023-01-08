package com.example.jobis.global.security;

import com.example.jobis.domain.user.domain.enums.Authority;
import com.example.jobis.global.security.jwt.JwtTokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtTokenProvider jwtTokenProvider;
    private final ObjectMapper objectMapper;
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.
                csrf().disable()
                .cors().and()


                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()

                .authorizeRequests()
                //company
                .antMatchers(HttpMethod.POST, "/companies").permitAll()
                .antMatchers(HttpMethod.GET, "/companies/exists/**").permitAll()
                .antMatchers(HttpMethod.POST, "/companies/recruitment").hasAuthority(Authority.COMPANY.toString())
                .antMatchers(HttpMethod.POST, "/recruit").hasAuthority(Authority.COMPANY.toString())
                .antMatchers(HttpMethod.GET, "/companies/{company-id}").hasAnyAuthority(Authority.ADMIN.toString(), Authority.STUDENT.toString())
                .antMatchers(HttpMethod.GET, "recruit/{recruit-id}").hasAnyAuthority(Authority.ADMIN.toString(), Authority.STUDENT.toString())

                //user
                .antMatchers(HttpMethod.POST, "/users/login").permitAll()
                .antMatchers(HttpMethod.PUT, "/users/reissue").permitAll()

                //file
                .antMatchers(HttpMethod.POST, "/files").permitAll()

                //code
                .antMatchers(HttpMethod.GET, "/code/tech").permitAll()
                .antMatchers(HttpMethod.GET, "/code/job").permitAll()

                //student
                .antMatchers(HttpMethod.POST, "/students").permitAll()
                .antMatchers(HttpMethod.POST, "/students/code").permitAll()
                .antMatchers(HttpMethod.PATCH, "/students/code").permitAll()
                .antMatchers(HttpMethod.GET, "/code/job").permitAll()
                .anyRequest().authenticated()
                .and()
                .apply(new FilterConfig(jwtTokenProvider, objectMapper));
        return http.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
