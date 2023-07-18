package team.retum.jobis.domain.application.persistence.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class QueryFieldTraineesVO {

    private final Long applicationId;
    private final Integer grade;
    private final Integer classRoom;
    private final Integer number;
    private final String studentName;
    private final LocalDate startDate;
    private final LocalDate endDate;

    @QueryProjection
    public QueryFieldTraineesVO(Long applicationId, Integer grade, Integer classRoom, Integer number,
                                String studentName, LocalDate startDate, LocalDate endDate) {
        this.applicationId = applicationId;
        this.grade = grade;
        this.classRoom = classRoom;
        this.number = number;
        this.studentName = studentName;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
