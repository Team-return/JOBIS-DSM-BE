package team.retum.jobis.domain.application.persistence.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import team.retum.jobis.domain.application.spi.vo.PassedApplicationStudentsVO;

@Getter
public class QueryPassedApplicationStudentsVO extends PassedApplicationStudentsVO {

    @QueryProjection
    public QueryPassedApplicationStudentsVO(Long applicationId, String studentName, Integer grade, Integer classRoom, Integer number) {
        super(applicationId, studentName, grade, classRoom, number);
    }
}
