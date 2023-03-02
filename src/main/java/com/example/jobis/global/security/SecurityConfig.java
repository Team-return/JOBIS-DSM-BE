package com.example.jobis.global.security;

import com.example.jobis.domain.user.domain.enums.Authority;
import com.example.jobis.global.security.jwt.JwtTokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.operator.AADProcessor;
import org.hibernate.bytecode.internal.bytebuddy.PassThroughInterceptor;
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
public class
SecurityConfig {
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
                .antMatchers(HttpMethod.GET, "/applications/company").hasAuthority(Authority.COMPANY.name())
                .antMatchers(HttpMethod.GET, "/applications/students").hasAnyAuthority(Authority.STUDENT.name())
                .antMatchers(HttpMethod.POST, "/applications/{company-id}").hasAuthority(Authority.STUDENT.name())
                .antMatchers(HttpMethod.DELETE, "/applications/{application-id}").hasAuthority(Authority.STUDENT.name())
                .antMatchers(HttpMethod.GET, "/applications/{company-id}").hasAuthority(Authority.TEACHER.name())
                .antMatchers(HttpMethod.GET, "/applications/{recruitment-id}").hasAuthority(Authority.TEACHER.name())

                //company
                .antMatchers(HttpMethod.POST, "/companies").permitAll()
                .antMatchers(HttpMethod.GET, "/companies/my").hasAuthority(Authority.COMPANY.name())
                .antMatchers(HttpMethod.PATCH, "/companies").hasAuthority(Authority.COMPANY.name())
                .antMatchers(HttpMethod.GET, "/companies/exists/{business-number}").permitAll()
                .antMatchers(HttpMethod.POST, "/companies/recruitment").hasAuthority(Authority.COMPANY.name())
                .antMatchers(HttpMethod.GET, "/companies/{company-id}")
                .hasAnyAuthority(Authority.STUDENT.toString(), Authority.TEACHER.toString())

                //user
                .antMatchers(HttpMethod.POST, "/users/login").permitAll()
                .antMatchers(HttpMethod.PUT, "/users/reissue").permitAll()

                //file
                .antMatchers(HttpMethod.POST, "/files").permitAll()
                .antMatchers(HttpMethod.DELETE, "/files").permitAll()

                //recruitment
                .antMatchers(HttpMethod.POST, "/recruitment").hasAuthority(Authority.COMPANY.name())
                .antMatchers(HttpMethod.PATCH, "/recruitment/{recruit-id}").hasAuthority(Authority.COMPANY.name())
                .antMatchers(HttpMethod.PATCH, "/recruitment/area/{recruit-area-id}").hasAuthority(Authority.COMPANY.name())
                .antMatchers(HttpMethod.DELETE, "/recruitment/area/code").hasAuthority(Authority.COMPANY.name())
                .antMatchers(HttpMethod.POST, "/recruitment/area/code/{recruit-area-id}").hasAuthority(Authority.COMPANY.name())
                .antMatchers(HttpMethod.POST, "recruitment/area").hasAuthority(Authority.COMPANY.name())

                //code
                .antMatchers(HttpMethod.GET, "/code/tech").permitAll()
                .antMatchers(HttpMethod.GET, "/code/job").permitAll()

                //student
                .antMatchers(HttpMethod.POST, "/students").permitAll()
                .antMatchers(HttpMethod.POST, "/students/code").permitAll()
                .antMatchers(HttpMethod.PATCH, "/students/code").permitAll()
                .antMatchers(HttpMethod.GET, "/students/recruitment").hasAuthority(Authority.STUDENT.name())
                .antMatchers(HttpMethod.GET, "/students/recruitment/{recruitment-id}").hasAuthority(Authority.STUDENT.name())

                //teacher
                .antMatchers(HttpMethod.PATCH, "/teachers/recruitment/{recruit-id}").hasAuthority(Authority.TEACHER.name())
                .antMatchers(HttpMethod.PATCH, "/teachers/{recruit-id}").hasAuthority(Authority.TEACHER.name())
                .antMatchers(HttpMethod.PATCH, "/teachers/area/{recruit-area-id}").hasAuthority(Authority.TEACHER.name())
                .antMatchers(HttpMethod.DELETE, "/teachers/area/code").hasAuthority(Authority.TEACHER.toString())
                .antMatchers(HttpMethod.POST, "/teachers/area/code/{recruit-area-id}").hasAuthority(Authority.TEACHER.name())
                .antMatchers(HttpMethod.POST, "/teachers/area").hasAuthority(Authority.TEACHER.name())
                .antMatchers(HttpMethod.GET, "/teachers/recruitment").hasAuthority(Authority.TEACHER.name())
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
