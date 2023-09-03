package team.retum.jobis.event.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import team.retum.jobis.domain.auth.model.Authority;
import team.retum.jobis.domain.notification.model.Notification;
import team.retum.jobis.domain.notification.model.Topic;
import team.retum.jobis.domain.notification.spi.CommandNotificationPort;
import team.retum.jobis.domain.notification.spi.NotificationPort;
import team.retum.jobis.domain.user.model.User;
import team.retum.jobis.event.notification.model.GroupNotificationEvent;
import team.retum.jobis.event.notification.model.NotificationEvent;
import team.retum.jobis.event.notification.model.SingleNotificationEvent;
import team.retum.jobis.thirdparty.fcm.FCMUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class NotificationEventHandler {

    private final FCMUtil fcmUtil;
    private final CommandNotificationPort commandNotificationPort;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onNotificationEvent(NotificationEvent event) {
        if (event instanceof SingleNotificationEvent singleEvent) {
            commandNotificationPort.saveNotification(
                    Notification.builder()
                            .title(getTitle(singleEvent.getTopic()))
                            .content(getContent(singleEvent.getTopic()))
                            .userId(singleEvent.getUser().getId())
                            .topic(singleEvent.getTopic())
                            .detailId(singleEvent.getDetailId())
                            .authority(Authority.STUDENT)
                            .isNew(true)
                            .build()
            );

            fcmUtil.sendMessage(
                    getTitle(singleEvent.getTopic()),
                    getContent(singleEvent.getTopic()),
                    singleEvent.getUser().getDeviceToken()
            );
        } else if (event instanceof GroupNotificationEvent groupEvent) {
            List<Notification> notifications = new ArrayList<>();
            List<String> deviceTokens = new ArrayList<>();

            groupEvent.getIdUsersMap().forEach(
                    (key, value) -> {
                        for (User user : value) {
                            notifications.add(
                                    Notification.builder()
                                            .title(getTitle(groupEvent.getTopic()))
                                            .content(getContent(groupEvent.getTopic()))
                                            .userId(user.getId())
                                            .topic(groupEvent.getTopic())
                                            .detailId(key)
                                            .authority(Authority.STUDENT)
                                            .isNew(true)
                                            .build()
                            );
                            deviceTokens.add(user.getDeviceToken());
                        }
                    }
            );

            commandNotificationPort.saveAllNotification(notifications);
            fcmUtil.sendMessages(
                    getTitle(groupEvent.getTopic()),
                    getContent(groupEvent.getTopic()),
                    deviceTokens
            );
        }
    }

    private String getTitle(Topic topic) {
        return switch (topic) {
            case INTERVIEW_SOON -> "면접 3일전";
            case NEW_APPLICATION -> "새로운 지원서";
            case NEW_RECRUITMENT -> "새로운 모집의뢰서";
            case RECRUITMENT_DONE -> "모집의뢰서 종료";
            case APPLICATION_STATUS_CHANGED -> "지원서 상태 변경";
            case RECRUITMENT_STATUS_CHANGED -> "모집의뢰서 상태 변경";
        };
    }

    private String getContent(Topic topic) {
        return switch (topic) {
            case INTERVIEW_SOON -> "면접이 3일 뒤에 있습니다. 준비하세요!";
            case NEW_APPLICATION -> "새로운 지원서가 등록되었습니다.";
            case NEW_RECRUITMENT -> "새로운 모집의뢰서가 등록되었습니다.";
            case RECRUITMENT_DONE -> "모집의뢰서가 종료되었습니다.";
            case APPLICATION_STATUS_CHANGED -> "지원서의 상태가 변경되었습니다.";
            case RECRUITMENT_STATUS_CHANGED -> "모집의뢰서의 상태가 변경되었습니다.";
        };
    }

}
