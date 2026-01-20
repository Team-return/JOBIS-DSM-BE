package team.retum.jobis.domain.interview.spi;

import team.retum.jobis.domain.interview.model.Interview;
import team.retum.jobis.domain.interview.spi.vo.InterviewVO;

import java.util.List;

public interface QueryInterviewPort {

    List<InterviewVO> getInterviewsByMonth(Integer year, Integer month);

    List<InterviewVO> getAllInterviews();

    List<Interview> getByIds(List<Long> interviewIds);

    List<Interview> getByDocumentNumberId(Long documentNumberId);
}
