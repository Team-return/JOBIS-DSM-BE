package team.retum.jobis.global.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import team.retum.jobis.global.security.jwt.JwtParser;

import static team.retum.jobis.domain.auth.model.Authority.COMPANY;
import static team.retum.jobis.domain.auth.model.Authority.DEVELOPER;
import static team.retum.jobis.domain.auth.model.Authority.STUDENT;
import static team.retum.jobis.domain.auth.model.Authority.TEACHER;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtParser jwtParser;
    private final ObjectMapper objectMapper;
    private final ApplicationEventPublisher eventPublisher;

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(CsrfConfigurer::disable)
                .cors(Customizer.withDefaults())
                .sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize ->
                        authorize
                                // healthCheck
                                .requestMatchers(HttpMethod.GET, "/").permitAll()

                                // actuator
                                .requestMatchers(HttpMethod.GET, "/actuator/**").permitAll()

                                // recruitments
                                .requestMatchers(HttpMethod.POST, "/recruitments/{company-id}").permitAll()
                                .requestMatchers(HttpMethod.GET, "/recruitments/my").hasAuthority(COMPANY.name())
                                .requestMatchers(HttpMethod.PATCH, "/recruitments/{recruitment-id}").hasAnyAuthority(TEACHER.name())
                                .requestMatchers(HttpMethod.PATCH, "/recruitments/area/{recruit-area-id}").hasAnyAuthority(TEACHER.name())
                                .requestMatchers(HttpMethod.POST, "/recruitments/{recruitment-id}/area").hasAnyAuthority(TEACHER.name())
                                .requestMatchers(HttpMethod.GET, "/recruitments/student").hasAnyAuthority(STUDENT.name(), DEVELOPER.name())
                                .requestMatchers(HttpMethod.GET, "/recruitments/student/count").hasAnyAuthority(STUDENT.name(), DEVELOPER.name())
                                .requestMatchers(HttpMethod.GET, "/recruitments/{recruitment-id}").hasAnyAuthority(STUDENT.name(), TEACHER.name())
                                .requestMatchers(HttpMethod.GET, "/recruitments/teacher").hasAuthority(TEACHER.name())
                                .requestMatchers(HttpMethod.GET, "/recruitments/teacher/count").hasAuthority(TEACHER.name())
                                .requestMatchers(HttpMethod.PATCH, "/recruitments/status").hasAuthority(TEACHER.name())
                                .requestMatchers(HttpMethod.DELETE, "/recruitments/{recruitment-id}").hasAnyAuthority(COMPANY.name(), TEACHER.name())
                                .requestMatchers(HttpMethod.DELETE, "/recruitments/area/{recruit-area-id}").hasAnyAuthority(TEACHER.name())

                                // bugs
                                .requestMatchers(HttpMethod.GET, "/bugs").hasAuthority(DEVELOPER.name())
                                .requestMatchers(HttpMethod.GET, "/bugs/{bug-report-id}").hasAuthority(DEVELOPER.name())
                                .requestMatchers(HttpMethod.POST, "/bugs").hasAuthority(STUDENT.name())

                                // students
                                .requestMatchers(HttpMethod.GET, "/students/my").hasAnyAuthority(STUDENT.name(), DEVELOPER.name())
                                .requestMatchers(HttpMethod.POST, "/students").permitAll()
                                .requestMatchers(HttpMethod.PATCH, "/students/forgotten_password").permitAll()
                                .requestMatchers(HttpMethod.GET, "/students/exists").permitAll()
                                .requestMatchers(HttpMethod.PATCH, "/students/profile").hasAnyAuthority(STUDENT.name(), DEVELOPER.name())
                                .requestMatchers(HttpMethod.GET, "/students/password").hasAnyAuthority(STUDENT.name(), DEVELOPER.name())
                                .requestMatchers(HttpMethod.PATCH, "/students/password").hasAnyAuthority(STUDENT.name(), DEVELOPER.name())

                                // applications
                                .requestMatchers(HttpMethod.GET, "/applications").hasAuthority(TEACHER.name())
                                .requestMatchers(HttpMethod.GET, "/applications/count").hasAuthority(TEACHER.name())
                                .requestMatchers(HttpMethod.GET, "/applications/employment/count").permitAll()
                                .requestMatchers(HttpMethod.GET, "/applications/pass/{company-id}").hasAnyAuthority(TEACHER.name())
                                .requestMatchers(HttpMethod.GET, "/applications/company").hasAuthority(COMPANY.name())
                                .requestMatchers(HttpMethod.GET, "/applications/students").hasAnyAuthority(STUDENT.name(), DEVELOPER.name())
                                .requestMatchers(HttpMethod.POST, "/applications/{company-id}").hasAnyAuthority(STUDENT.name(), DEVELOPER.name())
                                .requestMatchers(HttpMethod.DELETE, "/applications/{application-id}").hasAuthority(STUDENT.name())
                                .requestMatchers(HttpMethod.PATCH, "/applications/status").hasAnyAuthority(TEACHER.name())
                                .requestMatchers(HttpMethod.PATCH, "/applications/train-date").hasAuthority(TEACHER.name())
                                .requestMatchers(HttpMethod.PATCH, "/applications/rejection/{application-id}").hasAuthority(TEACHER.name())
                                .requestMatchers(HttpMethod.PUT, "/applications/{application-id}").hasAnyAuthority(STUDENT.name(), DEVELOPER.name())
                                .requestMatchers(HttpMethod.GET, "/applications/rejection/{application-id}").hasAnyAuthority(STUDENT.name(), DEVELOPER.name())

                                // bookmarks
                                .requestMatchers(HttpMethod.POST, "/bookmarks/{recruitment-id}").hasAnyAuthority(STUDENT.name(), DEVELOPER.name())
                                .requestMatchers(HttpMethod.DELETE, "/bookmarks/{recruitment-id}").hasAnyAuthority(STUDENT.name(), DEVELOPER.name())
                                .requestMatchers(HttpMethod.GET, "/bookmarks").hasAnyAuthority(STUDENT.name(), DEVELOPER.name())

                                // auth
                                .requestMatchers(HttpMethod.POST, "/auth/code").permitAll()
                                .requestMatchers(HttpMethod.PATCH, "/auth/code").permitAll()
                                .requestMatchers(HttpMethod.PUT, "/auth/reissue").permitAll()

                                // companies
                                .requestMatchers(HttpMethod.GET, "/companies/teacher").hasAuthority(TEACHER.name())
                                .requestMatchers(HttpMethod.GET, "/companies/teacher/count").hasAuthority(TEACHER.name())
                                .requestMatchers(HttpMethod.PATCH, "/companies/type").hasAuthority(TEACHER.name())
                                .requestMatchers(HttpMethod.PATCH, "/companies/mou").hasAuthority(TEACHER.name())
                                .requestMatchers(HttpMethod.POST, "/companies").permitAll()
                                .requestMatchers(HttpMethod.GET, "/companies/my").hasAuthority(COMPANY.name())
                                .requestMatchers(HttpMethod.PATCH, "/companies/{company-id}").hasAnyAuthority(COMPANY.name(), TEACHER.name())
                                .requestMatchers(HttpMethod.GET, "/companies/exists/{business-number}").permitAll()
                                .requestMatchers(HttpMethod.POST, "/companies/recruitment").hasAuthority(COMPANY.name())
                                .requestMatchers(HttpMethod.GET, "/companies/{company-id}").hasAnyAuthority(STUDENT.name(), TEACHER.name(), DEVELOPER.name())
                                .requestMatchers(HttpMethod.GET, "/companies/student").hasAnyAuthority(STUDENT.name(), DEVELOPER.name())
                                .requestMatchers(HttpMethod.GET, "/companies/student/count").hasAnyAuthority(STUDENT.name(), DEVELOPER.name())
                                .requestMatchers(HttpMethod.GET, "/companies/employment").hasAuthority(TEACHER.name())
                                .requestMatchers(HttpMethod.GET, "/companies/employment/count").hasAuthority(TEACHER.name())
                                .requestMatchers(HttpMethod.GET, "/companies/review").hasAnyAuthority(STUDENT.name(), DEVELOPER.name())

                                // users
                                .requestMatchers(HttpMethod.POST, "/users/login").permitAll()

                                // files
                                .requestMatchers(HttpMethod.POST, "/files").permitAll()
                                .requestMatchers(HttpMethod.POST, "/files/pre_signed").permitAll()
                                .requestMatchers(HttpMethod.DELETE, "/files").permitAll()

                                // code
                                .requestMatchers(HttpMethod.GET, "/codes").permitAll()

                                // acceptance
                                .requestMatchers(HttpMethod.GET, "/acceptances/{company-id}").hasAuthority(TEACHER.name())
                                .requestMatchers(HttpMethod.PATCH, "/acceptances/field-train/{application-id}").hasAnyAuthority(TEACHER.name())
                                .requestMatchers(HttpMethod.PATCH, "/acceptances/contract-date").hasAuthority(TEACHER.name())
                                .requestMatchers(HttpMethod.POST, "/acceptances/employment").hasAnyAuthority(TEACHER.name())
                                .requestMatchers(HttpMethod.PATCH, "/acceptances/field-train").hasAnyAuthority(TEACHER.name())
                                .requestMatchers(HttpMethod.DELETE, "/acceptances").hasAnyAuthority(TEACHER.name())

                                // review
                                .requestMatchers(HttpMethod.GET, "/reviews/{company-id}").hasAnyAuthority(STUDENT.name(), TEACHER.name())
                                .requestMatchers(HttpMethod.GET, "/reviews/details/{review-id}").hasAnyAuthority(STUDENT.name(), TEACHER.name())
                                .requestMatchers(HttpMethod.POST, "/reviews").hasAnyAuthority(STUDENT.name(), DEVELOPER.name())
                                .requestMatchers(HttpMethod.DELETE, "/reviews/{review-id}").hasAuthority(TEACHER.name())

                                // banner
                                .requestMatchers(HttpMethod.POST, "/banners").hasAuthority(TEACHER.name())
                                .requestMatchers(HttpMethod.DELETE, "/banners/{banner-id}").hasAuthority(TEACHER.name())
                                .requestMatchers(HttpMethod.GET, "/banners").hasAuthority(STUDENT.name())

                                // notice
                                .requestMatchers(HttpMethod.POST, "/notices").hasAuthority(TEACHER.name())
                                .requestMatchers(HttpMethod.PATCH, "/notices/{notice-id}").hasAuthority(TEACHER.name())
                                .requestMatchers(HttpMethod.DELETE, "/notices/{notice-id}").hasAuthority(TEACHER.name())

                                // notification
                                .requestMatchers(HttpMethod.GET, "/notifications").authenticated()
                                .requestMatchers(HttpMethod.PATCH, "/notifications/{notification-id}").authenticated()
                                .anyRequest().authenticated()

                )
                .apply(new FilterConfig(jwtParser, objectMapper, eventPublisher));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
