package team.returm.jobis.domain.application.domain.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import team.returm.jobis.domain.application.domain.enums.ApplicationStatus;

@Getter
public class QueryApplyCompaniesVO {

    private final String companyName;
    private final ApplicationStatus status;

    @QueryProjection
    public QueryApplyCompaniesVO(String companyName, ApplicationStatus status) {
        this.companyName = companyName;
        this.status = status;
    }
}
