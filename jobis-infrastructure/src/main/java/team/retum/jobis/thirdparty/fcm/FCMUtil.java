package team.retum.jobis.thirdparty.fcm;

import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.ApnsConfig;
import com.google.firebase.messaging.Aps;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.MulticastMessage;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.application.event.ApplicationsStatusChangedEvent;
import team.retum.jobis.domain.notification.exception.DeviceTokenNotFoundException;
import team.retum.jobis.domain.notification.exception.FailedToSubscriptionException;
import team.retum.jobis.domain.notification.exception.FailedToUnsubscriptionException;
import team.retum.jobis.domain.notification.model.Notification;
import team.retum.jobis.domain.notification.model.Topic;
import team.retum.jobis.event.application.message.ApplicationMessage;
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

    public ApplicationMessage applicationMessage(ApplicationsStatusChangedEvent event, String companyName) {
        String title;
        String content;

        switch (event.getStatus()) {
            case REQUESTED:
                title = "지원서 제출 완료";
                content = "지원서가 제출되었습니다. 검토가 진행될 예정입니다. 조금만 기다려 주세요!";
                break;

            case APPROVED:
                title = "지원서가 승인되었습니다!";
                content = "지원서가 승인되었어요. 곧 회사에 전송될 거예요!";
                break;

            case SEND:
                title = "지원서 전송 완료";
                content = "'" + companyName + "'에 지원서가 성공적으로 전송되었습니다. 좋은 결과를 기원합니다!";
                break;

            case PROCESSING:
                title = "지원서가 진행중입니다";
                content = "'" + companyName + "'에서 지원서를 검토 중입니다. 진행 상황을 계속 확인해 주세요.";
                break;

            case FAILED:
                title = companyName + "지원서 탈락";
                content = "아쉽게도 '" + companyName + "'의 지원에서 탈락하셨습니다. 다음 기회에 더 좋은 결과가 있길 바랍니다.";
                break;

            case PASS:
                title = "(취뽀) " + companyName + "에 합격하셨습니다!! 🥳";
                content = "합격을 진심으로 축하드립니다.";
                break;

            case FIELD_TRAIN:
                title = "현장실습이 확정되었습니다!";
                content = "'" + companyName + "'에서 현장실습이 확정되었습니다. 새로운 경험을 통해 성장하시길 바랍니다!";
                break;

            case ACCEPTANCE:
                title = "근로계약이 체결됬습니다!";
                content = "'" + companyName + "'과의 근로계약이 체결됬습니다. 새로운 여정을 응원합니다!";
                break;

            case REJECTED:
                title = "지원서가 반려되었습니다.";
                content = "지원서가 '" + companyName + "'에 의해 반려되었습니다. 지원서 내용을 다시 확인하고 수정 후 재제출해 주세요.";
                break;

            default:
                title = "지원서 상태가 변경되었습니다";
                content = "지원서 상태가 " + event.getStatus().getName() + "으로 변경되었습니다.";
                break;
        }

        return new ApplicationMessage(title, content);
    }
}
