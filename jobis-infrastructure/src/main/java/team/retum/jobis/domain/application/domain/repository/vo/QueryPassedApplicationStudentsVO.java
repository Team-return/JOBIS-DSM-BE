package team.retum.jobis.domain.application.domain.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class QueryPassedApplicationStudentsVO {

    private final Long applicationId;
    private final String studentName;
    private final Integer grade;
    private final Integer classRoom;
    private final Integer number;

    @QueryProjection
    public QueryPassedApplicationStudentsVO(Long applicationId, String studentName, Integer grade, Integer classRoom, Integer number) {
        this.applicationId = applicationId;
        this.studentName = studentName;
        this.grade = grade;
        this.classRoom = classRoom;
        this.number = number;
    }
}
