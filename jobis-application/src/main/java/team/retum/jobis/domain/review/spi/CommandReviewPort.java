package team.retum.jobis.domain.review.spi;

import team.retum.jobis.domain.review.model.QnA;
import team.retum.jobis.domain.review.model.Review;

import java.util.List;

public interface CommandReviewPort {

    Review save(Review review);

    void saveAll(List<QnA> qnAs);

    void delete(Review review);
}
