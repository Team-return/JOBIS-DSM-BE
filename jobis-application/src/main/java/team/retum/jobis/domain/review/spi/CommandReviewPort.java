package team.retum.jobis.domain.review.spi;

import team.retum.jobis.domain.review.model.Review;

public interface CommandReviewPort {
    void saveReview(Review review);

    void deleteReview(Review review);
}
