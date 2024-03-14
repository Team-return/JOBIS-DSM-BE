package team.retum.jobis.domain.recruitment.persistence.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.code.persistence.entity.CodeEntity;
import team.retum.jobis.domain.recruitment.spi.vo.MyAllRecruitmentsVO;
import team.retum.jobis.domain.recruitment.spi.vo.RecruitmentAreaElement;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class QueryMyAllRecruitmentsVO extends MyAllRecruitmentsVO {

    @QueryProjection
    public QueryMyAllRecruitmentsVO(Long id, List<CodeEntity> codes, int hiredCount, LocalDateTime createdAt) {
        super(id, new RecruitmentAreaElement(codes.stream().map(CodeEntity::getKeyword).toList(), hiredCount), createdAt);
    }
}
