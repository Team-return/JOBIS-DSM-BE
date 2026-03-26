package team.retum.jobis.thirdparty.smtp;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@RequiredArgsConstructor
@Configuration
public class SmtpConfig {
    private final SmtpProperties smtpProperties;

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        mailSender.setDefaultEncoding("UTF-8");
        mailSender.setHost(smtpProperties.getHost());
        mailSender.setPort(smtpProperties.getPort());
        mailSender.setUsername(smtpProperties.getUsername());
        mailSender.setPassword(smtpProperties.getPassword());

        Properties javaMailProperties = mailSender.getJavaMailProperties();

        javaMailProperties.putAll(smtpProperties.getProperties());
        return mailSender;
    }
}
