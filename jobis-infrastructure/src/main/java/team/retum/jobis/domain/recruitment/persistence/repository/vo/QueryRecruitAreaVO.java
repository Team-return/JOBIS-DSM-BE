package team.retum.jobis.domain.recruitment.persistence.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import team.retum.jobis.domain.code.model.CodeResponse;
import team.retum.jobis.domain.code.model.CodeType;
import team.retum.jobis.domain.code.persistence.entity.CodeEntity;
import team.retum.jobis.domain.recruitment.dto.response.RecruitAreaResponse;

import java.util.List;

@Getter
public class QueryRecruitAreaVO extends RecruitAreaResponse {

    @QueryProjection
    public QueryRecruitAreaVO(Long id, Integer hiredCount, String majorTask, String preferentialTreatment, List<CodeEntity> codes) {
        super(
                id,
                codes.stream()
                        .filter(code -> code.getType().equals(CodeType.JOB))
                        .map(code -> new CodeResponse(code.getCode(), code.getKeyword()))
                        .toList(),
                codes.stream()
                        .filter(code -> code.getType().equals(CodeType.TECH))
                        .map(code -> new CodeResponse(code.getCode(), code.getKeyword()))
                        .toList(),
                hiredCount,
                majorTask,
                preferentialTreatment
        );
    }
}
