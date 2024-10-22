package team.retum.jobis.thirdparty.python;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "python")
public class PythonProperty {

    private final String base_url;
}
