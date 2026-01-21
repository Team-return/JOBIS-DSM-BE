package team.retum.jobis.domain.interview.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.Service;
import team.retum.jobis.common.spi.PublishEventPort;
import team.retum.jobis.domain.auth.model.Authority;
import team.retum.jobis.domain.interview.dto.InterviewMessage;
import team.retum.jobis.domain.interview.model.Interview;
import team.retum.jobis.domain.interview.model.InterviewTiming;
import team.retum.jobis.domain.notification.model.Notification;
import team.retum.jobis.domain.notification.model.Topic;
import team.retum.jobis.domain.notification.spi.CommandNotificationPort;
import team.retum.jobis.domain.user.model.User;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class SendInterviewNotificationService {

    private final CommandNotificationPort commandNotificationPort;
    private final PublishEventPort publishEventPort;

    public void execute(User user, Interview interview, InterviewTiming timing, LocalDate targetDate) {
        if (user.getToken() == null || user.getToken().isEmpty()) {
            return;
        }

        InterviewMessage message = InterviewMessage.of(
            timing,
            interview.getCompanyName(),
            interview.getInterviewTime(),
            interview.getLocation(),
            targetDate
        );

        Notification notification = Notification.builder()
            .title(message.getTitle())
            .content(message.getContent())
            .userId(user.getId())
            .deviceToken(user.getToken())
            .detailId(interview.getId())
            .topic(Topic.INTERVIEW)
            .authority(Authority.STUDENT)
            .isNew(true)
            .build();

        commandNotificationPort.save(notification);
        publishEventPort.publishEvent(notification);
    }
}
