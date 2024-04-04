package team.retum.jobis.event.notice;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import team.retum.jobis.domain.auth.model.Authority;
import team.retum.jobis.domain.notice.event.NoticePostedEvent;
import team.retum.jobis.domain.notification.model.Notification;
import team.retum.jobis.domain.notification.model.Topic;
import team.retum.jobis.domain.notification.spi.CommandNotificationPort;
import team.retum.jobis.domain.user.model.User;
import team.retum.jobis.domain.user.spi.QueryUserPort;
import team.retum.jobis.thirdparty.fcm.FCMUtil;

import java.util.List;

@RequiredArgsConstructor
@Component
public class NoticeEventHandler {

    private final CommandNotificationPort commandNotificationPort;
    private final QueryUserPort queryUserPort;
    private final FCMUtil fcmUtil;

    @Async("asyncTaskExecutor")
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onNoticePosted(NoticePostedEvent event) {
        List<User> users = queryUserPort.queryAllUsers();

        Notification notification = Notification.builder()
                .title(event.getNotice().getTitle())
                .content(event.getNotice().getContent())
                .topic(Topic.NEW_NOTICE)
                .detailId(event.getNotice().getId())
                .authority(Authority.STUDENT)
                .isNew(true)
                .build();

        for (User user : users) {
            if (user.isSubscribed()) {
                commandNotificationPort.saveNotification(notification);
                fcmUtil.sendMessageToTopic(notification);
            }
        }
    }
}
