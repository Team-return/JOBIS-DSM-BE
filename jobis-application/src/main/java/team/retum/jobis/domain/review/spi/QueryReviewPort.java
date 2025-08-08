package team.retum.jobis.domain.review.spi;

import team.retum.jobis.domain.review.dto.ReviewFilter;
import team.retum.jobis.domain.review.model.Review;
import team.retum.jobis.domain.review.spi.vo.QnAVO;
import team.retum.jobis.domain.review.spi.vo.ReviewDetailVO;
import team.retum.jobis.domain.review.spi.vo.ReviewVO;

import java.util.List;
import java.util.Optional;

public interface QueryReviewPort {

    boolean existsByCompanyIdAndStudentId(Long companyId, Long studentId);

    Review getByIdOrThrow(Long reviewId);

    List<ReviewVO> getAllByFilter(ReviewFilter filter);

    Long getCountBy(ReviewFilter filter);

    Optional<ReviewDetailVO> getDetailById(Long reviewId);

    List<QnAVO> getQnAVOById(Long reviewId);

    boolean existsById(Long reviewId);
}
