package team.retum.jobis.domain.application.persistence.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import team.retum.jobis.domain.application.spi.vo.TotalApplicationCountVO;

@Getter
public class QueryTotalApplicationCountVO extends TotalApplicationCountVO {

    @QueryProjection
    public QueryTotalApplicationCountVO(Long totalStudentCount, Long passedCount, Long approvedCount) {
        super(totalStudentCount, passedCount, approvedCount);
    }
}
