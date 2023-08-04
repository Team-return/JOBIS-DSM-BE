package team.retum.jobis.domain.company.persistence.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import team.retum.jobis.domain.company.spi.vo.TeacherEmployCompaniesVO;

@Getter
public class QueryTeacherEmployCompaniesVO extends TeacherEmployCompaniesVO {

    @QueryProjection
    public QueryTeacherEmployCompaniesVO(Long companyId, String companyName, Long fieldTraineeCount, Long contractCount) {
        super(companyId, companyName, fieldTraineeCount, contractCount);
    }
}
