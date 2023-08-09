package team.retum.jobis.domain.review.spi;

import team.retum.jobis.domain.review.model.Review;
import team.retum.jobis.domain.review.spi.vo.QnAsVO;
import team.retum.jobis.domain.review.spi.vo.ReviewsVO;

import java.util.List;
import java.util.Optional;

public interface QueryReviewPort {
    boolean existsByCompanyIdAndStudentName(Long companyId, String studentName);

    Optional<Review> queryReviewById(Long reviewId);

    List<QnAsVO> queryAllQnAsByReviewId(Long reviewId);

    List<ReviewsVO> queryAllReviewsByCompanyId(Long companyId);
}
