package team.retum.jobis.thirdparty.webhook.slack;

import net.gpedro.integrations.slack.SlackApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SlackConfig {

    @Value("${slack.url}")
    private String url;

    @Value("${slack.token}")
    private String token;

    @Bean
    public SlackApi slackApi() {
        return new SlackApi(url + token);
    }
}
