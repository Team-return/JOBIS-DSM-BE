package team.retum.jobis.domain.acceptance.persistence.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import team.retum.jobis.domain.acceptance.spi.vo.AcceptanceVO;

import java.time.LocalDate;

@Getter
public class QueryAcceptanceVO extends AcceptanceVO {

    @QueryProjection
    public QueryAcceptanceVO(Long acceptanceId, Integer grade, Integer classRoom, Integer number, String studentName, LocalDate contractDate) {
        super(acceptanceId, grade, classRoom, number, studentName, contractDate);
    }
}
