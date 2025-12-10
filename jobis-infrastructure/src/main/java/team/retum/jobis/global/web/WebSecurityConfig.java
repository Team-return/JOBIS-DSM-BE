package team.retum.jobis.global.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebSecurityConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
            .addMapping("/**")
            .allowedOrigins(
                "https://jobis-admin.team-return.com",
                "https://jobis-company.team-return.com",
                "https://jobis-student.team-return.com",
                "https://jobis-company-dev.team-return.com",
                "https://jobis-admin-dev.team-return.com",
                "https://develop.jobis-student.pages.dev",
                "https://jobis-student-dev.team-return.com",
                "http://localhost:3000",
                "http://localhost:3001",
                "http://localhost:4000",
                "http://localhost:5173",
                "http://localhost:5174",
                "http://localhost:5175",

                "https://jobis-admin.team-return.monster",
                "https://jobis-company.team-return.monster",
                "https://jobis-student.team-return.monster",
                "https://jobis-company-dev.team-return.monster",
                "https://jobis-admin-dev.team-return.monster",
                "https://jobis-student-dev.team-return.monster",

                "https://jobis-admin.team-return.kr",
                "https://jobis-company.team-return.kr",
                "https://jobis-student.team-return.kr",
                "https://jobis-company-dev.team-return.kr",
                "https://jobis-admin-dev.team-return.kr",
                "https://jobis-student-dev.team-return.kr",

                "https://jobis-admin.dsmhs.kr",
                "https://jobis-company.dsmhs.kr",
                "https://jobis-student.dsmhs.kr",
                "https://jobis-admin-stag.dsmhs.kr",
                "https://jobis-company-stag.dsmhs.kr",
                "https://jobis-student-stag.dsmhs.kr"
            )
            .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "HEAD")
            .allowedHeaders("*");
    }
}
