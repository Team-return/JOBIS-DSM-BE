package team.retum.jobis.domain.interview.dto.response;

import lombok.Getter;
import team.retum.jobis.domain.interview.spi.vo.InterviewVO;
import team.retum.jobis.domain.recruitment.model.ProgressType;

import java.time.LocalDate;
import java.util.List;

@Getter
public class QueryInterviewsResponse {

    private final int totalCount;
    private final List<InterviewResponse> interviews;

    public QueryInterviewsResponse(List<InterviewVO> interviews) {
        this.totalCount = interviews.size();
        this.interviews = interviews.stream()
            .map(InterviewResponse::from)
            .toList();
    }

    @Getter
    public static class InterviewResponse {
        private final Long id;
        private final ProgressType interviewType;
        private final LocalDate startDate;
        private final LocalDate endDate;
        private final String interviewTime;
        private final String companyName;
        private final String location;

        private InterviewResponse(
            Long id,
            ProgressType interviewType,
            LocalDate startDate,
            LocalDate endDate,
            String interviewTime,
            String companyName,
            String location
        ) {
            this.id = id;
            this.interviewType = interviewType;
            this.startDate = startDate;
            this.endDate = endDate;
            this.interviewTime = interviewTime;
            this.companyName = companyName;
            this.location = location;
        }

        public static InterviewResponse from(InterviewVO vo) {
            return new InterviewResponse(
                vo.getId(),
                vo.getInterviewType(),
                vo.getStartDate(),
                vo.getEndDate(),
                vo.getInterviewTime(),
                vo.getCompanyName(),
                vo.getLocation()
            );
        }
    }
}
