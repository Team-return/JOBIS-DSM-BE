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
public class SendAfterInterviewNotificationUseCase {

    private final QueryInterviewPort queryInterviewPort;
    private final QueryUserPort queryUserPort;
    private final SendInterviewNotificationService sendInterviewNotificationService;

    public void execute() {
        LocalDate today = LocalDate.now();

        List<Interview> todayInterviews = queryInterviewPort.getInterviewsByDateRange(today);

        todayInterviews.forEach(interview -> {
            User user = queryUserPort.getByStudentId(interview.getStudentId());
            sendInterviewNotificationService.execute(user, interview, InterviewTiming.AFTER_INTERVIEW, today);
        });
    }
}
