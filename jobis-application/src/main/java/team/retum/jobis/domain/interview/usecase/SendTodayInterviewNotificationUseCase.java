package team.retum.jobis.domain.interview.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.PublishEventPort;
import team.retum.jobis.domain.interview.event.InterviewNotificationEvent;
import team.retum.jobis.domain.interview.model.Interview;
import team.retum.jobis.domain.interview.model.InterviewTiming;
import team.retum.jobis.domain.interview.spi.QueryInterviewPort;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@UseCase
public class SendTodayInterviewNotificationUseCase {

    private final QueryInterviewPort queryInterviewPort;
    private final PublishEventPort publishEventPort;

    public void execute() {
        LocalDate today = LocalDate.now();

        sendNotificationsForDate(today);
    }

    private void sendNotificationsForDate(LocalDate targetDate) {
        List<Interview> interviews = queryInterviewPort.getInterviewsByDateRange(targetDate);

        interviews.forEach(interview ->
            publishEventPort.publishEvent(new InterviewNotificationEvent(interview, InterviewTiming.TODAY, targetDate))
        );
    }
}
