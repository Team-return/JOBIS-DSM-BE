package team.returm.jobis.global.security.jwt;

import java.util.Base64;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@ConstructorBinding
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    private final String secret;
    private final String header;
    private final String prefix;
    private final Long accessExp;
    private final Long refreshExp;

    public JwtProperties(String secret, String header, String prefix
            , Long accessExp, Long refreshExp) {
        this.secret = Base64.getEncoder().encodeToString(secret.getBytes());
        this.header = header;
        this.prefix = prefix;
        this.accessExp = accessExp * 1000;
        this.refreshExp = refreshExp;
    }
}
