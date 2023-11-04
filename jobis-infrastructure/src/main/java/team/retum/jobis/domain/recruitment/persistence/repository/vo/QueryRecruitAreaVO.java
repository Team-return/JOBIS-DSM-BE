package team.retum.jobis.domain.recruitment.persistence.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import team.retum.jobis.domain.code.persistence.entity.CodeEntity;
import team.retum.jobis.domain.recruitment.dto.response.RecruitAreaResponse;

import java.util.List;

@Getter
public class QueryRecruitAreaVO extends RecruitAreaResponse {

    @QueryProjection
    public QueryRecruitAreaVO(Long id, Integer hiredCount, String majorTask,
                              List<String> jobCodes, String preferentialTreatment, List<CodeEntity> techCode) {
        super(
                id,
                jobCodes,
                techCode.stream()
                        .map(CodeEntity::getKeyword)
                        .toList(),
                hiredCount,
                majorTask,
                preferentialTreatment
        );
    }
}
