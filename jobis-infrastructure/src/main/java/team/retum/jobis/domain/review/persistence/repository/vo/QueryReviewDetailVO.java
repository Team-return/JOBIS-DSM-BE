package team.retum.jobis.domain.review.persistence.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import team.retum.jobis.domain.review.model.InterviewLocation;
import team.retum.jobis.domain.review.model.InterviewType;
import team.retum.jobis.domain.review.spi.vo.ReviewDetailVO;

@Getter
public class QueryReviewDetailVO extends ReviewDetailVO {

    @QueryProjection
    public QueryReviewDetailVO(Long reviewId, String companyName, String writer,
                         int year, String major, InterviewType type,
                         InterviewLocation location, Integer interviewerCount,
                         String question, String answer) {
        super(reviewId, companyName, writer, year, major, type,
            location, interviewerCount, question, answer);
    }
}
