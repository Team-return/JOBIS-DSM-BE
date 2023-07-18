package team.retum.jobis.domain.recruitment.persistence.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import team.retum.jobis.domain.code.persistence.entity.CodeEntity;

import java.util.List;

@Getter
public class RecruitAreaVO {

    private final Long id;

    private final Integer hiredCount;

    private final String majorTask;

    private final String jobCodes;

    private final List<CodeEntity> techCodeEntities;

    @QueryProjection
    public RecruitAreaVO(Long id, Integer hiredCount, String majorTask, String jobCodes, List<CodeEntity> techCodeEntities) {
        this.id = id;
        this.hiredCount = hiredCount;
        this.majorTask = majorTask;
        this.jobCodes = jobCodes;
        this.techCodeEntities = techCodeEntities;
    }
}
