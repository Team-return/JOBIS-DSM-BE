package team.retum.jobis.thirdparty.fcm;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.MulticastMessage;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.notification.model.Notification;
import team.retum.jobis.domain.notification.model.Topic;
import team.retum.jobis.global.exception.FailedSendingMessagesException;

import java.util.List;

@Component
public class FCMUtil {

    public void subscribeTopic(Topic topic, Long detailId, List<String> tokens) {
        try {
            FirebaseMessaging.getInstance().subscribeToTopic(
                    tokens,
                    createTopicName(topic, detailId)
            );
        } catch (FirebaseMessagingException e) {
            throw FailedSendingMessagesException.EXCEPTION;
        }
    }

    public void unsubscribeTopic(Topic topic, Long detailId, List<String> tokens) {
        try {
            FirebaseMessaging.getInstance().unsubscribeFromTopic(
                    tokens,
                    createTopicName(topic, detailId)
            );
        } catch (FirebaseMessagingException e) {
            throw FailedSendingMessagesException.EXCEPTION;
        }
    }

    public void sendMessagesByTopic(Topic topic, Long detailId, Notification notification) {
        Message message = Message.builder()
                .setTopic(createTopicName(topic, detailId))
                .setNotification(com.google.firebase.messaging.Notification.builder()
                        .setTitle(notification.getTitle())
                        .setBody(notification.getContent())
                        .build()
                )
                .putData("detail_id", notification.getDetailId().toString())
                .build();

        FirebaseMessaging.getInstance().sendAsync(message);
    }

    public void sendMessage(String title, String content, String token) {
        Message message = Message.builder()
                .setToken(token)
                .setNotification(
                        com.google.firebase.messaging.Notification.builder()
                                .setTitle(title)
                                .setBody(content)
                                .build()
                )
                .build();

        FirebaseMessaging.getInstance().sendAsync(message);
    }

    public void sendMessages(String title, String content, List<String> tokens) {
        try {
            MulticastMessage message = MulticastMessage.builder()
                    .addAllTokens(tokens)
                    .setNotification(com.google.firebase.messaging.Notification.builder()
                            .setTitle(title)
                            .setBody(content)
                            .build()
                    )
                    .build();

            FirebaseMessaging.getInstance().sendEachForMulticast(message);
        } catch (FirebaseMessagingException e) {
            throw FailedSendingMessagesException.EXCEPTION;
        }
    }

    private String createTopicName(Topic topic, Long detailId) {
        return topic.name() + ":" + detailId.toString();
    }
}
