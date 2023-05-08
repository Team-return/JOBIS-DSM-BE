package team.returm.jobis.global.web;

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
                        "http://localhost:4000",
                        "http://localhost:4001",
                        "https://jobis-admin.team-return.com"
                )
                .allowedMethods("*")
                .allowedHeaders("*");
    }
}
