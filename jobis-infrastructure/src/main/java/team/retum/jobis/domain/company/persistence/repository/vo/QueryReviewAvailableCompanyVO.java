package team.retum.jobis.domain.company.persistence.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import team.retum.jobis.domain.company.dto.response.QueryReviewAvailableCompaniesResponse.CompanyResponse;

public class QueryReviewAvailableCompanyVO extends CompanyResponse {

    @QueryProjection
    public QueryReviewAvailableCompanyVO(Long id, String name) {
        super(id, name);
    }
}
