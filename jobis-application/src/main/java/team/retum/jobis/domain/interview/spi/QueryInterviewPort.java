package team.retum.jobis.domain.interview.spi;

import team.retum.jobis.domain.interview.model.Interview;

import java.time.LocalDate;
import java.util.List;

public interface QueryInterviewPort {

    List<Interview> getInterviewsByDateRange(LocalDate targetDate);
}
