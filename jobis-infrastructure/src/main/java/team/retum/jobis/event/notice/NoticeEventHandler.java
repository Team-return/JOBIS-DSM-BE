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
import team.retum.jobis.event.RabbitMqProducer;

import java.util.List;

@Component
@RequiredArgsConstructor
public class NoticeEventHandler {

    private final CommandNotificationPort commandNotificationPort;
    private final QueryUserPort queryUserPort;
    private final RabbitMqProducer rabbitMqProducer;

    @Async("asyncTaskExecutor")
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onNoticePosted(NoticePostedEvent event) {
        List<String> deviceTokens = queryUserPort.getDeviceTokenByTopic(Topic.NOTICE);

        deviceTokens.forEach(deviceToken -> {
            User user = queryUserPort.getUserIdByDeviceToken(deviceToken);

            Notification notification = Notification.builder()
                .title(event.getNotice().getTitle())
                .content(event.getNotice().getContent())
                .userId(user.getId())
                .deviceToken(deviceToken)
                .detailId(event.getNotice().getId())
                .topic(Topic.NOTICE)
                .authority(Authority.STUDENT)
                .isNew(true)
                .build();

            commandNotificationPort.save(notification);
            rabbitMqProducer.publishEvent(notification);
        });
    }
}
