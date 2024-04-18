package team.retum.jobis.domain.review.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.review.model.Review;
import team.retum.jobis.domain.review.spi.ReviewPort;

@RequiredArgsConstructor
@UseCase
public class DeleteReviewUseCase {

    private final ReviewPort reviewPort;

    public void execute(Long reviewId) {
        Review review = reviewPort.getByIdOrThrow(reviewId);
        reviewPort.delete(review);
    }
}
