package team.retum.jobis.domain.review.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.domain.review.dto.response.QnAResponse;
import team.retum.jobis.domain.review.dto.response.QueryReviewDetailResponse;
import team.retum.jobis.domain.review.exception.ReviewNotFoundException;
import team.retum.jobis.domain.review.spi.QueryReviewPort;
import team.retum.jobis.domain.review.spi.vo.ReviewDetailVO;

import java.util.List;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class QueryReviewDetailUseCase {

    private final QueryReviewPort queryReviewPort;

    public QueryReviewDetailResponse execute(Long reviewId) {
        if (reviewId != null && !queryReviewPort.existsById(reviewId)) {
            throw ReviewNotFoundException.EXCEPTION;
        }

        ReviewDetailVO reviewDetail = queryReviewPort.getDetailById(reviewId)
            .orElseThrow(() -> ReviewNotFoundException.EXCEPTION);
        List<QnAResponse> qnAs =
            queryReviewPort.getQnAVOById(reviewId)
                .stream()
                .map(QnAResponse::from)
                .toList();

        return QueryReviewDetailResponse.from(reviewDetail, qnAs);
    }
}
