package team.retum.jobis.domain.interview.spi;

import java.time.LocalDate;
import java.util.List;

import team.retum.jobis.domain.interview.dto.InterviewFilter;
import team.retum.jobis.domain.interview.model.Interview;

public interface QueryInterviewPort {

    List<Interview> getInterviewsByDateRange(LocalDate targetDate);

    List<Interview> getInterviewsBy(InterviewFilter filter);

    List<Interview> getByIds(List<Long> interviewIds);

    List<Interview> getByDocumentNumberId(Long documentNumberId);
}
