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
import team.retum.jobis.thirdparty.fcm.FCMUtil;

@RequiredArgsConstructor
@Component
public class NoticeEventHandler {

    private final FCMUtil fcmUtil;

    @Async("asyncTaskExecutor")
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onNoticePosted(NoticePostedEvent event) {

        Notification notification = Notification.builder()
                .title(event.getNotice().getTitle())
                .content(event.getNotice().getContent())
                .topic(Topic.NEW_NOTICE)
                .detailId(event.getNotice().getId())
                .authority(Authority.STUDENT)
                .isNew(true)
                .build();

        fcmUtil.sendMessageToTopic(notification);
    }
}
