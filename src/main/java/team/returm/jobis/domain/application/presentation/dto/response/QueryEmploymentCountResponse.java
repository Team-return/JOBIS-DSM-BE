package team.returm.jobis.domain.application.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;
import team.returm.jobis.domain.application.domain.repository.vo.QueryApplyCompaniesVO;

import java.util.List;

@Getter
@Builder
public class QueryEmploymentCountResponse {

    private final Long totalStudentCount;
    private final Long passCount;
    private final Long approvedCount;
}
