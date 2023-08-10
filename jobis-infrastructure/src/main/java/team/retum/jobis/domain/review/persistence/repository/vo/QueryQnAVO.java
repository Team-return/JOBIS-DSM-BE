package team.retum.jobis.domain.review.persistence.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import team.retum.jobis.domain.review.spi.vo.QnAVO;

@Getter
public class QueryQnAVO extends QnAVO {

    @QueryProjection
    public QueryQnAVO(String question, String answer, String keyword) {
        super(question, answer, keyword);
    }
}
