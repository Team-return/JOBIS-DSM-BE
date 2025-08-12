package team.retum.jobis.domain.review.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.domain.review.dto.QuestionElement;
import team.retum.jobis.domain.review.dto.response.QueryReviewOptionsResponse;
import team.retum.jobis.domain.review.model.InterviewLocation;
import team.retum.jobis.domain.review.model.InterviewType;
import team.retum.jobis.domain.review.spi.QueryQuestionPort;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class QueryReviewOptionsUseCase {

    private final QueryQuestionPort queryQuestionPort;

    public QueryReviewOptionsResponse execute() {
        List<QuestionElement> questions = queryQuestionPort.queryAllQuestions().stream()
            .map(question -> new QuestionElement(question.getId(), question.getQuestion()))
            .toList();

        return QueryReviewOptionsResponse.builder()
            .interviewTypes(Arrays.stream(InterviewType.values()).map(InterviewType::name).toList())
            .locations(Arrays.stream(InterviewLocation.values()).map(InterviewLocation::name).toList())
            .questions(questions)
            .build();
    }
}
