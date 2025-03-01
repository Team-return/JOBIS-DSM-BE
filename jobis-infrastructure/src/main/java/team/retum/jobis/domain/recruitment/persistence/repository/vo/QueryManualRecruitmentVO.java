package team.retum.jobis.domain.recruitment.persistence.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import team.retum.jobis.domain.recruitment.spi.vo.ManualRecruitmentVO;

@Getter
public class QueryManualRecruitmentVO extends ManualRecruitmentVO {

    @QueryProjection
    public QueryManualRecruitmentVO(Long id, String companyName, String companyLogoUrl) {
        super(id, companyName, companyLogoUrl);
    }
}
