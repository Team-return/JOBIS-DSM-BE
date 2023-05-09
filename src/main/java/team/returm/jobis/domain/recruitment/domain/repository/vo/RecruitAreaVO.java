package team.returm.jobis.domain.recruitment.domain.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import team.returm.jobis.domain.code.domain.Code;

import java.util.List;

@Getter
public class RecruitAreaVO {

    private final Long id;

    private final Integer hiredCount;

    private final String majorTask;

    private final String jobCodes;

    private final List<Code> techCodes;

    @QueryProjection
    public RecruitAreaVO(Long id, Integer hiredCount, String majorTask, String jobCodes, List<Code> techCodes) {
        this.id = id;
        this.hiredCount = hiredCount;
        this.majorTask = majorTask;
        this.jobCodes = jobCodes;
        this.techCodes = techCodes;
    }
}