package team.retum.jobis.domain.review.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.code.model.Code;
import team.retum.jobis.domain.code.spi.QueryCodePort;
import team.retum.jobis.domain.review.dto.QueryReviewDetailResponse;
import team.retum.jobis.domain.review.dto.QueryReviewDetailResponse.QnAResponse;
import team.retum.jobis.domain.review.exception.ReviewNotFoundException;
import team.retum.jobis.domain.review.model.QnAElement;
import team.retum.jobis.domain.review.model.Review;
import team.retum.jobis.domain.review.spi.QueryReviewPort;

import java.util.List;

@RequiredArgsConstructor
@UseCase
public class QueryReviewDetailUseCase {

    private final QueryReviewPort queryReviewPort;
    private final QueryCodePort queryCodePort;

    public QueryReviewDetailResponse execute(String reviewId) {
        Review review = queryReviewPort.findReviewById(reviewId)
                .orElseThrow(() -> ReviewNotFoundException.EXCEPTION);

        List<Long> codeIds = review.getQnAElements().stream()
                .map(QnAElement::getCodeId)
                .toList();

        List<Code> codeEntities = queryCodePort.queryCodesByIdIn(codeIds);

        return QueryReviewDetailResponse.builder()
                .year(review.getYear())
                .writer(review.getStudentName())
                .qnaResponses(
                        QnAResponse.of(review.getQnAElements(), codeEntities)
                )
                .build();
    }
}
