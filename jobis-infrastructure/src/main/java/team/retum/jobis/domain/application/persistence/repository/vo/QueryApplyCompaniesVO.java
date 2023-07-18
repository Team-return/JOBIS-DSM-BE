package team.retum.jobis.domain.application.persistence.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import com.example.jobisapplication.domain.application.model.ApplicationStatus;

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
