package team.returm.jobis.domain.application.domain.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;

@Getter
public class QueryTotalApplicationCountVO {

    private final Long passedCount;
    private final Long approvedCount;

    @Builder
    @QueryProjection
    public QueryTotalApplicationCountVO(Long passedCount, Long approvedCount) {
        this.passedCount = passedCount;
        this.approvedCount = approvedCount;
    }
}
