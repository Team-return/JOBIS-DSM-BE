package team.retum.jobis.thirdparty.fcm;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import team.retum.jobis.global.util.LogUtil;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;

@Slf4j
@Configuration
public class FirebaseConfig {

    @Value("${fcm.path}")
    private String path;

    @PostConstruct
    public void init() {
        try (FileInputStream account = new FileInputStream(path)) {
            FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(account))
                .build();

            FirebaseApp.initializeApp(options);
        } catch (Exception e) {
            log.error(LogUtil.stackTraceToString(e));
        }
    }
}
