package team.retum.jobis.domain.interview.persistence.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import team.retum.jobis.domain.interview.spi.vo.InterviewVO;
import team.retum.jobis.domain.recruitment.model.ProgressType;

import java.time.LocalDate;

@Getter
public class QueryInterviewVO extends InterviewVO {

    @QueryProjection
    public QueryInterviewVO(
        Long id,
        ProgressType interviewType,
        LocalDate startDate,
        LocalDate endDate,
        String interviewTime,
        String companyName,
        String location
    ) {
        super(id, interviewType, startDate, endDate, interviewTime, companyName, location);
    }
}
