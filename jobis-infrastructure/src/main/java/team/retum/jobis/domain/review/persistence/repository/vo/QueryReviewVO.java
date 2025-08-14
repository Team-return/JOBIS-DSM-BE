package team.retum.jobis.domain.review.persistence.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import team.retum.jobis.domain.review.spi.vo.ReviewVO;

import java.time.LocalDateTime;

@Getter
public class QueryReviewVO extends ReviewVO {

    @QueryProjection
    public QueryReviewVO(Long reviewId, String companyName, String companyLogoUrl, String writer, LocalDateTime createdAt, String major) {
        super(reviewId, companyName, companyLogoUrl, writer, createdAt.getYear(), major);
    }
}
