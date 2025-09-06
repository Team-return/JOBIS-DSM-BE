package team.retum.jobis.domain.review.persistence.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import team.retum.jobis.domain.review.spi.vo.MyReviewVO;

@Getter
public class QueryMyReviewVO extends MyReviewVO {

    @QueryProjection
    public QueryMyReviewVO(Long reviewId, String companyName) {
        super(reviewId, companyName);
    }
}
