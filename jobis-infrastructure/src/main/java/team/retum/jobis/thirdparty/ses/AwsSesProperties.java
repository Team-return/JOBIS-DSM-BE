package team.retum.jobis.thirdparty.ses;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties("cloud.aws.ses")
public class AwsSesProperties {

    private final String source;

}
