package team.retum.jobis.thirdparty.smtp;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "spring.mail")
public class SmtpProperties {
    private final String host;
    private final int port;
    private final String username;
    private final String password;

    private final Map<String, String> properties;
}
