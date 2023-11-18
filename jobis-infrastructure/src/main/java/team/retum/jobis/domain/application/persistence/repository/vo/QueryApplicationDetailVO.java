package team.retum.jobis.domain.application.persistence.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import team.retum.jobis.domain.application.model.ApplicationStatus;
import team.retum.jobis.domain.application.spi.vo.ApplicationDetailVO;

@Getter
public class QueryApplicationDetailVO extends ApplicationDetailVO {

    @QueryProjection
    public QueryApplicationDetailVO(Long id, Long studentId, Long companyId, String businessArea, ApplicationStatus status) {
        super(id, studentId, companyId, businessArea, status);
    }
}
