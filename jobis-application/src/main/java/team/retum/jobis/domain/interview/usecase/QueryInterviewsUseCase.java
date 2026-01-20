package team.retum.jobis.domain.interview.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.domain.interview.dto.response.QueryInterviewsResponse;
import team.retum.jobis.domain.interview.spi.QueryInterviewPort;
import team.retum.jobis.domain.interview.spi.vo.InterviewVO;

import java.util.List;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class QueryInterviewsUseCase {

    private final QueryInterviewPort queryInterviewPort;

    public QueryInterviewsResponse execute(Integer year, Integer month) {
        List<InterviewVO> interviews;

        if (year != null && month != null) {
            interviews = queryInterviewPort.getInterviewsByMonth(year, month);
        } else {
            interviews = queryInterviewPort.getAllInterviews();
        }

        return new QueryInterviewsResponse(interviews);
    }
}
