package team.retum.jobis.domain.company.persistence.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class TeacherQueryEmployCompaniesVO {

    private final Long companyId;
    private final String companyName;
    private final Long fieldTraineeCount;
    private final Long contractCount;

    @QueryProjection
    public TeacherQueryEmployCompaniesVO(Long companyId, String companyName, Long fieldTraineeCount, Long contractCount) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.fieldTraineeCount = fieldTraineeCount;
        this.contractCount = contractCount;
    }
}
