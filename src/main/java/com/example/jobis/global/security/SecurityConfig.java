package com.example.jobis.global.security;

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
import static com.example.jobis.domain.user.domain.enums.Authority.TEACHER;
import static com.example.jobis.domain.user.domain.enums.Authority.STUDENT;
import static com.example.jobis.domain.user.domain.enums.Authority.COMPANY;

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
                //application
                .antMatchers(HttpMethod.GET, "/applications/company").hasAuthority(COMPANY.name())
                .antMatchers(HttpMethod.GET, "/applications/students").hasAnyAuthority(STUDENT.name())
                .antMatchers(HttpMethod.POST, "/applications/{company-id}").hasAuthority(STUDENT.name())
                .antMatchers(HttpMethod.DELETE, "/applications/{application-id}").hasAuthority(STUDENT.name())
                .antMatchers(HttpMethod.GET, "/applications/{company-id}").hasAuthority(TEACHER.name())
                .antMatchers(HttpMethod.GET, "/applications/{recruitment-id}").hasAuthority(TEACHER.name())

                //company
                .antMatchers(HttpMethod.POST, "/companies").permitAll()
                .antMatchers(HttpMethod.GET, "/companies/my").hasAuthority(COMPANY.name())
                .antMatchers(HttpMethod.PATCH, "/companies").hasAuthority(COMPANY.name())
                .antMatchers(HttpMethod.GET, "/companies/exists/{business-number}").permitAll()
                .antMatchers(HttpMethod.POST, "/companies/recruitment").hasAuthority(COMPANY.name())
                .antMatchers(HttpMethod.GET, "/companies/{company-id}").hasAnyAuthority(STUDENT.name(), TEACHER.name())

                //user
                .antMatchers(HttpMethod.POST, "/users/login").permitAll()
                .antMatchers(HttpMethod.PUT, "/users/reissue").permitAll()

                //file
                .antMatchers(HttpMethod.POST, "/files").permitAll()
                .antMatchers(HttpMethod.DELETE, "/files").permitAll()

                //recruitment
                .antMatchers(HttpMethod.POST, "/recruitment").hasAuthority(COMPANY.name())
                .antMatchers(HttpMethod.PATCH, "/recruitment/{recruitment-id}").hasAnyAuthority(COMPANY.name(), TEACHER.name())
                .antMatchers(HttpMethod.PATCH, "/recruitment/area/{recruit-area-id}").hasAnyAuthority(STUDENT.name(), TEACHER.name())
                .antMatchers(HttpMethod.POST, "/recruitment/{recruitment-id}/area").hasAnyAuthority(STUDENT.name(), TEACHER.name())
                .antMatchers(HttpMethod.GET, "/recruitment/student").hasAuthority(STUDENT.name())
                .antMatchers(HttpMethod.GET, "/recruitment/teacher").hasAuthority(TEACHER.name())
                .antMatchers(HttpMethod.PATCH, "/recruitment/{recruitment-id}/status").hasAuthority(TEACHER.name())

                //code
                .antMatchers(HttpMethod.GET, "/code/tech").permitAll()
                .antMatchers(HttpMethod.GET, "/code/job").permitAll()

                //student
                .antMatchers(HttpMethod.POST, "/students").permitAll()
                .antMatchers(HttpMethod.POST, "/students/code").permitAll()
                .antMatchers(HttpMethod.PATCH, "/students/code").permitAll()

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
