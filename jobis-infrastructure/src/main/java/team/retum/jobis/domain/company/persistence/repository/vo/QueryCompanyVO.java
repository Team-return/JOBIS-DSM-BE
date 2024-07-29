package team.retum.jobis.domain.company.persistence.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import team.retum.jobis.domain.company.spi.vo.CompanyVO;

@Getter
public class QueryCompanyVO extends CompanyVO {

    @QueryProjection
    public QueryCompanyVO(Long id, String companyName, String logoUrl) {
        super(id, companyName, logoUrl);
    }
}
