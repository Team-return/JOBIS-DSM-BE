package team.retum.jobis.domain.application.persistence.repository.vo;

import com.example.jobisapplication.domain.application.spi.vo.TotalApplicationCountVO;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class QueryTotalApplicationCountVO extends TotalApplicationCountVO {

    @QueryProjection
    public QueryTotalApplicationCountVO(Long totalStudentCount, Long passedCount, Long approvedCount) {
        super(totalStudentCount, passedCount, approvedCount);
    }
}
