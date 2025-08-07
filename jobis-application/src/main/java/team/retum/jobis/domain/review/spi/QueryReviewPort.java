package team.retum.jobis.domain.review.spi;

import team.retum.jobis.domain.review.dto.ReviewFilter;
import team.retum.jobis.domain.review.model.Review;
import team.retum.jobis.domain.review.spi.vo.ReviewVO;

import java.util.List;

public interface QueryReviewPort {

    boolean existsByCompanyIdAndStudentId(Long companyId, Long studentId);

    Review getByIdOrThrow(Long reviewId);

    List<ReviewVO> getAllByFilter(ReviewFilter filter);

    Long getCountBy(ReviewFilter filter);
}
