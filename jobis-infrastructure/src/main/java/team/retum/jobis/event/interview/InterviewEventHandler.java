package team.retum.jobis.event.interview;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import team.retum.jobis.domain.auth.model.Authority;
import team.retum.jobis.domain.interview.dto.InterviewMessage;
import team.retum.jobis.domain.interview.event.InterviewNotificationEvent;
import team.retum.jobis.domain.notification.model.Notification;
import team.retum.jobis.domain.notification.model.Topic;
import team.retum.jobis.domain.notification.spi.CommandNotificationPort;
import team.retum.jobis.domain.user.model.User;
import team.retum.jobis.domain.user.spi.QueryUserPort;
import team.retum.jobis.event.RabbitMqProducer;

@Component
@RequiredArgsConstructor
public class InterviewEventHandler {

    private final CommandNotificationPort commandNotificationPort;
    private final QueryUserPort queryUserPort;
    private final RabbitMqProducer rabbitMqProducer;

    @Async("asyncTaskExecutor")
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onInterviewNotification(InterviewNotificationEvent event) {
        User user = queryUserPort.getByStudentId(event.getInterview().getStudentId());

        if (user.getToken() == null || user.getToken().isEmpty()) {
            return;
        }

        InterviewMessage message = InterviewMessage.of(
            event.getTiming(),
            event.getInterview().getCompanyName(),
            event.getInterview().getInterviewTime(),
            event.getInterview().getLocation(),
            event.getTargetDate()
        );

        Notification notification = Notification.builder()
            .title(message.getTitle())
            .content(message.getContent())
            .userId(user.getId())
            .deviceToken(user.getToken())
            .detailId(event.getInterview().getId())
            .topic(Topic.INTERVIEW)
            .authority(Authority.STUDENT)
            .isNew(true)
            .build();

        commandNotificationPort.save(notification);
        rabbitMqProducer.publishEvent(notification);
    }
}
