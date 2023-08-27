package team.retum.jobis.thirdparty.fcm;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.MulticastMessage;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.notification.model.Notification;
import team.retum.jobis.global.exception.FailedSendingMessagesException;

import java.util.List;

@Component
public class FCMUtil {

    public void sendMessage(Notification notification, String token) {
        Message message = Message.builder()
                .setToken(token)
                .setNotification(
                        com.google.firebase.messaging.Notification.builder()
                                .setTitle(notification.getTitle())
                                .setBody(notification.getContent())
                                .build()
                )
                .putData("detail_id", notification.getDetailId().toString())
                .putData("topic", notification.getTopic().toString())
                .build();

        FirebaseMessaging.getInstance().sendAsync(message);
    }

    public void sendMessages(Notification notification, List<String> tokens) {
        try {
            MulticastMessage message = MulticastMessage.builder()
                    .addAllTokens(tokens)
                    .setNotification(com.google.firebase.messaging.Notification.builder()
                            .setTitle(notification.getTitle())
                            .setBody(notification.getContent())
                            .build()
                    )
                    .build();

            FirebaseMessaging.getInstance().sendEachForMulticast(message);
        } catch (FirebaseMessagingException e) {
            throw FailedSendingMessagesException.EXCEPTION;
        }
    }
}
