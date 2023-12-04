package team.retum.jobis.thirdparty.api.client;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;


@Getter
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "openfeign")
public class FeignProperty {

    private final String accessKey;
}
