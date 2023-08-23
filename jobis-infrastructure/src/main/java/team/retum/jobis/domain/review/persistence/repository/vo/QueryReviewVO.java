package team.retum.jobis.domain.review.persistence.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import team.retum.jobis.domain.review.spi.vo.ReviewVO;

import java.time.LocalDateTime;

@Getter
public class QueryReviewVO extends ReviewVO {

    @QueryProjection
    public QueryReviewVO(Long reviewId, String writer, LocalDateTime createdAt) {
        super(reviewId, writer, createdAt);
    }
}
