package team.retum.jobis.thirdparty.fcm;

import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.ApnsConfig;
import com.google.firebase.messaging.Aps;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.MulticastMessage;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.notification.exception.DeviceTokenNotFoundException;
import team.retum.jobis.domain.notification.exception.FailedToSubscriptionException;
import team.retum.jobis.domain.notification.exception.FailedToUnsubscriptionException;
import team.retum.jobis.domain.notification.model.Notification;
import team.retum.jobis.domain.notification.model.Topic;
import team.retum.jobis.global.exception.FailedSendingMessagesException;

import java.util.Arrays;
import java.util.List;

@Component
public class FCMUtil {

    public void subscribeTopic(String token, Topic topic) {
        if (token == null || token.isEmpty()) {
            throw new DeviceTokenNotFoundException();
        }

        try {
            FirebaseMessaging.getInstance().subscribeToTopicAsync(Arrays.asList(token), topic.toString()).get();
        } catch (Exception e) {
            throw new FailedToSubscriptionException();
        }
    }

    public void unsubscribeTopic(String token, Topic topic) {
        if (token == null || token.isEmpty()) {
            throw new DeviceTokenNotFoundException();
        }

        try {
            FirebaseMessaging.getInstance().unsubscribeFromTopicAsync(Arrays.asList(token), topic.toString()).get();
        } catch (Exception e) {
            throw new FailedToUnsubscriptionException();
        }
    }

    private com.google.firebase.messaging.Notification.Builder buildNotification(Notification notification) {
        return com.google.firebase.messaging.Notification.builder()
            .setTitle(notification.getTitle())
            .setBody(notification.getContent());
    }

    private AndroidConfig buildAndroidConfig(Notification notification) {
        return AndroidConfig.builder()
            .putData("detail_id", notification.getDetailId().toString())
            .putData("topic", notification.getTopic().toString())
            .build();
    }

    private ApnsConfig buildApnsConfig(Notification notification) {
        return ApnsConfig.builder()
            .setAps(Aps.builder()
                .setSound("default")
                .putCustomData("detail_id", notification.getDetailId().toString())
                .putCustomData("topic", notification.getTopic().toString())
                .build())
            .build();
    }

    public void sendMessages(Notification notification, List<String> tokens) {
        try {
            MulticastMessage message = MulticastMessage.builder()
                .addAllTokens(tokens)
                .setNotification(buildNotification(notification).build())
                .setAndroidConfig(buildAndroidConfig(notification))
                .setApnsConfig(buildApnsConfig(notification))
                .build();

            FirebaseMessaging.getInstance().sendEachForMulticast(message);
        } catch (FirebaseMessagingException e) {
            throw FailedSendingMessagesException.EXCEPTION;
        }
    }

    public void sendMessageToTopic(Notification notification) {
        try {
            Message message = Message.builder()
                .setTopic(notification.getTopic().toString())
                .setNotification(buildNotification(notification).build())
                .setAndroidConfig(buildAndroidConfig(notification))
                .setApnsConfig(buildApnsConfig(notification))
                .build();

            FirebaseMessaging.getInstance().send(message);
        } catch (FirebaseMessagingException e) {
            throw FailedSendingMessagesException.EXCEPTION;
        }
    }
}
