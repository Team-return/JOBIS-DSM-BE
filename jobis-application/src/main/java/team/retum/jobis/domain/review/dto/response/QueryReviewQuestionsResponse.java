package team.retum.jobis.domain.review.dto.response;

import lombok.Builder;
import team.retum.jobis.domain.review.dto.QuestionElement;

import java.util.List;

@Builder
public record QueryReviewQuestionsResponse(
    List<QuestionElement> questions
) {

}
