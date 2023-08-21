package team.retum.jobis.domain.review.spi;

import team.retum.jobis.domain.review.model.Review;
import team.retum.jobis.domain.review.spi.vo.QnAVO;
import team.retum.jobis.domain.review.spi.vo.ReviewVO;

import java.util.List;
import java.util.Optional;

public interface QueryReviewPort {
    boolean existsByCompanyIdAndStudentId(Long companyId, Long studentId);

    Optional<Review> queryReviewById(Long reviewId);

    List<QnAVO> queryAllQnAsByReviewId(Long reviewId);

    List<ReviewVO> queryAllReviewsByCompanyId(Long companyId);
}
