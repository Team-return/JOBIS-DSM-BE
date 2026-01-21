package team.retum.jobis.domain.interview.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.interview.model.Interview;
import team.retum.jobis.domain.interview.model.InterviewTiming;
import team.retum.jobis.domain.interview.spi.QueryInterviewPort;
import team.retum.jobis.domain.user.model.User;
import team.retum.jobis.domain.user.spi.QueryUserPort;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@UseCase
public class SendUpcomingInterviewNotificationUseCase {

    private final QueryInterviewPort queryInterviewPort;
    private final QueryUserPort queryUserPort;
    private final SendInterviewNotificationService sendInterviewNotificationService;

    public void execute() {
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);
        LocalDate threeDaysLater = today.plusDays(3);

        sendNotificationsForDate(tomorrow, InterviewTiming.TOMORROW);
        sendNotificationsForDate(threeDaysLater, InterviewTiming.THREE_DAYS_LATER);
    }

    private void sendNotificationsForDate(LocalDate targetDate, InterviewTiming timing) {
        List<Interview> interviews = queryInterviewPort.getInterviewsByDateRange(targetDate);

        interviews.forEach(interview -> {
            User user = queryUserPort.getByStudentId(interview.getStudentId());
            sendInterviewNotificationService.execute(user, interview, timing, targetDate);
        });
    }
}

