package team.retum.jobis.domain.interview.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.retum.jobis.domain.interview.model.Interview;
import team.retum.jobis.domain.interview.model.InterviewTiming;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class InterviewNotificationEvent {

    private final Interview interview;
    private final InterviewTiming timing;
    private final LocalDate targetDate;
}
