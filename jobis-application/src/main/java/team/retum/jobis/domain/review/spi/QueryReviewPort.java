package team.retum.jobis.domain.review.spi;

import team.retum.jobis.domain.review.model.Review;
import team.retum.jobis.domain.review.spi.vo.QnAVO;
import team.retum.jobis.domain.review.spi.vo.ReviewVO;

import java.util.List;

public interface QueryReviewPort {

    boolean existsByCompanyIdAndStudentId(Long companyId, Long studentId);

    Review getByIdOrThrow(Long reviewId);

    List<QnAVO> getAllQnAsByReviewId(Long reviewId);

    List<ReviewVO> getAllByCompanyId(Long companyId);
}
