package team.retum.jobis.domain.student.persistence.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import team.retum.jobis.domain.student.spi.vo.TeacherStudentsVO;

@Getter
public class QueryTeacherStudentsVO extends TeacherStudentsVO {

    @QueryProjection
    public QueryTeacherStudentsVO(Long id, String name, Integer grade, Integer classRoom, Integer number) {
        super(id, name, grade, classRoom, number);
    }
}
