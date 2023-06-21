package team.returm.jobis.domain.application.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;
import team.returm.jobis.domain.application.domain.repository.vo.QueryTotalApplicationCountVO;

@Getter
@Builder
public class QueryEmploymentCountResponse {

    private final Long totalStudentCount;
    private final Long passedCount;
    private final Long approvedCount;

    public static QueryEmploymentCountResponse of(QueryTotalApplicationCountVO vo) {
        return QueryEmploymentCountResponse.builder()
                .totalStudentCount(vo.getTotalStudentCount())
                .passedCount(vo.getPassedCount())
                .approvedCount(vo.getApprovedCount())
                .build();
    }
}
