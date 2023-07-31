package team.retum.jobis.domain.application.persistence.repository.vo;

import team.retum.jobis.domain.application.spi.vo.TotalApplicationCountVO;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class QueryTotalApplicationCountVO extends TotalApplicationCountVO {

    @QueryProjection
    public QueryTotalApplicationCountVO(Long totalStudentCount, Long passedCount, Long approvedCount) {
        super(totalStudentCount, passedCount, approvedCount);
    }
}
