package team.retum.jobis.domain.review.spi;

import team.retum.jobis.domain.review.model.QnA;
import team.retum.jobis.domain.review.model.Review;

import java.util.List;

public interface CommandReviewPort {

    Review saveReview(Review review);

    void saveAllQnAs(List<QnA> qnAs);

    void deleteReview(Review review);
}
