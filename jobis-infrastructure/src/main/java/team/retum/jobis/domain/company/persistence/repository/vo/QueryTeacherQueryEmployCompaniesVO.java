package team.retum.jobis.domain.company.persistence.repository.vo;

import com.example.jobisapplication.domain.company.spi.vo.TeacherQueryEmployCompaniesVO;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class QueryTeacherQueryEmployCompaniesVO extends TeacherQueryEmployCompaniesVO {

    @QueryProjection
    public QueryTeacherQueryEmployCompaniesVO(Long companyId, String companyName, Long fieldTraineeCount, Long contractCount) {
        super(companyId, companyName, fieldTraineeCount, contractCount);
    }
}
