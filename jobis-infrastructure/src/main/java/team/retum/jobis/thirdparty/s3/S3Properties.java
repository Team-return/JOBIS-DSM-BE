package team.retum.jobis.thirdparty.s3;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties("cloud.aws.s3")
public class S3Properties {

    private final String bucket;
    private final String url;
}
