package team.retum.jobis.domain.review.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.domain.review.dto.QuestionElement;
import team.retum.jobis.domain.review.dto.response.QueryReviewQuestionsResponse;
import team.retum.jobis.domain.review.spi.QueryQuestionPort;

import java.util.List;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class QueryReviewQuestionsUseCase {

    private final QueryQuestionPort queryQuestionPort;

    public QueryReviewQuestionsResponse execute() {
        List<QuestionElement> questions = queryQuestionPort.queryAllQuestions().stream()
            .map(question -> new QuestionElement(question.getId(), question.getQuestion()))
            .toList();

        return QueryReviewQuestionsResponse.builder()
            .questions(questions)
            .build();
    }
}
