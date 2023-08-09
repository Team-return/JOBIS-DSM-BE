package team.retum.jobis.domain.review.persistence.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import team.retum.jobis.domain.review.spi.vo.QnAsVO;

@Getter
public class QueryQnAsVO extends QnAsVO {

    @QueryProjection
    public QueryQnAsVO(String question, String answer, String keyword) {
        super(question, answer, keyword);
    }
}
