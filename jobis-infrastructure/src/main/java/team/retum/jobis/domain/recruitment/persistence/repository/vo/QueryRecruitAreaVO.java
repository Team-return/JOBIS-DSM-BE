package team.retum.jobis.domain.recruitment.persistence.repository.vo;

import com.example.jobisapplication.domain.code.model.Code;
import com.example.jobisapplication.domain.recruitment.dto.response.RecruitAreaResponse;
import com.example.jobisapplication.domain.recruitment.spi.vo.RecruitAreaVO;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import team.retum.jobis.domain.code.persistence.entity.CodeEntity;

import java.util.List;

@Getter
public class QueryRecruitAreaVO extends RecruitAreaResponse {

    @QueryProjection
    public QueryRecruitAreaVO(Long id, Integer hiredCount, String majorTask, String jobCodes, List<CodeEntity> techCode) {
        super(
                id,
                jobCodes,
                techCode.stream()
                        .map(CodeEntity::getKeyword)
                        .toList(),
                hiredCount,
                majorTask
        );
    }
}
