package team.retum.jobis.domain.review.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.review.dto.response.QueryReviewDetailResponse;
import team.retum.jobis.domain.review.spi.QueryReviewPort;

@RequiredArgsConstructor
@UseCase
public class QueryReviewDetailUseCase {

    private final QueryReviewPort queryReviewPort;

    public QueryReviewDetailResponse execute(Long reviewId) {
        return new QueryReviewDetailResponse(
            queryReviewPort.getAllQnAsByReviewId(reviewId)
        );
    }
}
