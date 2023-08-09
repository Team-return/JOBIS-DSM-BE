package team.retum.jobis.domain.review.persistence.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import team.retum.jobis.domain.review.spi.vo.ReviewsVO;

import java.time.LocalDateTime;

@Getter
public class QueryReviewsVO extends ReviewsVO {

    @QueryProjection
    public QueryReviewsVO(Long reviewId, String writer, LocalDateTime createdAt) {
        super(reviewId, writer, createdAt.getYear());
    }
}
