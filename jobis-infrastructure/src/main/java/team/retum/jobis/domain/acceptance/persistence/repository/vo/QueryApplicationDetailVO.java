package team.retum.jobis.domain.acceptance.persistence.repository.vo;

import team.retum.jobis.domain.application.spi.vo.ApplicationDetailVO;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import team.retum.jobis.domain.application.model.ApplicationStatus;

@Getter
public class QueryApplicationDetailVO extends ApplicationDetailVO {

    @QueryProjection
    public QueryApplicationDetailVO(Long id, String studentName, int studentGrade, int studentClassNumber,
                                    int studentNumber, Long companyId, String businessArea, ApplicationStatus status) {
        super(id, studentName, studentGrade, studentClassNumber, studentNumber, companyId, businessArea, status);
    }
}
