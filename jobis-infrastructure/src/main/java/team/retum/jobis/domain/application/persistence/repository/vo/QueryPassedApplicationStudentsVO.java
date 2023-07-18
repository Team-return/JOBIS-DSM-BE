package team.retum.jobis.domain.application.persistence.repository.vo;

import com.example.jobisapplication.domain.application.spi.vo.PassedApplicationStudentsVO;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class QueryPassedApplicationStudentsVO extends PassedApplicationStudentsVO {

    @QueryProjection
    public QueryPassedApplicationStudentsVO(Long applicationId, String studentName, Integer grade, Integer classRoom, Integer number) {
        super(applicationId, studentName, grade, classRoom, number);
    }
}
