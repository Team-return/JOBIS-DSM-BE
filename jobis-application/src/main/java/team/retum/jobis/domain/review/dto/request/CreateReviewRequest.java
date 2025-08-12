package team.retum.jobis.domain.review.dto.request;

import lombok.Builder;
import team.retum.jobis.domain.review.model.InterviewLocation;
import team.retum.jobis.domain.review.model.InterviewType;

import java.util.List;

@Builder
public record CreateReviewRequest(
    InterviewType interviewType,
    InterviewLocation location,
    Long companyId,
    Long jobCode,
    Integer interviewerCount,
    List<CreateQnARequest> qnas,
    String question,
    String answer
) {

    @Builder
    public record CreateQnARequest(
        Long questionId,
        String answer
    ) {

    }
}
