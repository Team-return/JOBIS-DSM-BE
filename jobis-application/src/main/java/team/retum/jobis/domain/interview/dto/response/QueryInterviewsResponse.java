package team.retum.jobis.domain.interview.dto.response;

import lombok.Getter;
import team.retum.jobis.domain.interview.model.Interview;
import team.retum.jobis.domain.recruitment.model.ProgressType;

import java.time.LocalDate;
import java.util.List;

@Getter
public class QueryInterviewsResponse {

    private final int totalCount;
    private final List<InterviewResponse> interviews;

    public QueryInterviewsResponse(List<Interview> interviews) {
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
        private final Long studentId;
        private final Long documentNumberId;

        private InterviewResponse(
            Long id,
            ProgressType interviewType,
            LocalDate startDate,
            LocalDate endDate,
            String interviewTime,
            String companyName,
            String location,
            Long studentId,
            Long documentNumberId
        ) {
            this.id = id;
            this.interviewType = interviewType;
            this.startDate = startDate;
            this.endDate = endDate;
            this.interviewTime = interviewTime;
            this.companyName = companyName;
            this.location = location;
            this.studentId = studentId;
            this.documentNumberId = documentNumberId;
        }

        public static InterviewResponse from(Interview interview) {
            return new InterviewResponse(
                interview.getId(),
                interview.getInterviewType(),
                interview.getStartDate(),
                interview.getEndDate(),
                interview.getInterviewTime(),
                interview.getCompanyName(),
                interview.getLocation(),
                interview.getStudentId(),
                interview.getDocumentNumberId()
            );
        }
    }
}
