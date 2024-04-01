package team.retum.jobis.domain.review.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.review.exception.ReviewNotFoundException;
import team.retum.jobis.domain.review.model.Review;
import team.retum.jobis.domain.review.spi.CommandReviewPort;
import team.retum.jobis.domain.review.spi.QueryReviewPort;

@RequiredArgsConstructor
@UseCase
public class DeleteReviewUseCase {

    private final QueryReviewPort queryReviewPort;
    private final CommandReviewPort commandReviewPort;

    public void execute(Long reviewId) {
        Review review = queryReviewPort.queryReviewById(reviewId)
            .orElseThrow(() -> ReviewNotFoundException.EXCEPTION);

        commandReviewPort.deleteReview(review);
    }
}
