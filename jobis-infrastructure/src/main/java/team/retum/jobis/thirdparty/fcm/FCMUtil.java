package team.retum.jobis.thirdparty.fcm;

import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.ApnsConfig;
import com.google.firebase.messaging.Aps;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.MulticastMessage;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.notification.model.Notification;
import team.retum.jobis.global.exception.FailedSendingMessagesException;

import java.util.List;

@Component
public class FCMUtil {

    public void sendMessages(Notification notification, List<String> tokens) {
        try {
            MulticastMessage message = MulticastMessage.builder()
                    .addAllTokens(tokens)
                    .setNotification(com.google.firebase.messaging.Notification.builder()
                            .setTitle(notification.getTitle())
                            .setBody(notification.getContent())
                            .build()
                    )
                    .setAndroidConfig(
                            AndroidConfig.builder()
                                    .putData("detail_id", notification.getDetailId().toString())
                                    .putData("topic", notification.getTopic().toString())
                                    .build()
                    )
                    .setApnsConfig(
                            ApnsConfig.builder()
                                    .setAps(
                                            Aps.builder()
                                                    .setSound("default")
                                                    .putCustomData("detail_id", notification.getDetailId().toString())
                                                    .putCustomData("topic", notification.getTopic().toString())
                                                    .build()
                                    ).build()
                    )
                    .build();

            FirebaseMessaging.getInstance().sendEachForMulticast(message);
        } catch (FirebaseMessagingException e) {
            throw FailedSendingMessagesException.EXCEPTION;
        }
    }
}
