package team.retum.jobis.domain.review.spi;

import team.retum.jobis.domain.review.model.Review;

import java.util.List;
import java.util.Optional;

public interface QueryReviewPort {
    boolean existsByCompanyIdAndStudentName(Long companyId, String studentName);

    Optional<Review> findReviewById(String reviewId);

    List<Review> queryAllReviewsByCompanyId(Long companyId);

    Long queryReviewCountByCompanyId(Long companyId);
}
