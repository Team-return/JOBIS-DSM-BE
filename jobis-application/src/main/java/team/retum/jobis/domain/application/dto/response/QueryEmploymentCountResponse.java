package team.retum.jobis.domain.application.dto.response;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.domain.application.spi.vo.TotalApplicationCountVO;

@Getter
@Builder
public class QueryEmploymentCountResponse {

    private final Long totalStudentCount;
    private final Long passedCount;
    private final Long approvedCount;

    public static QueryEmploymentCountResponse of(TotalApplicationCountVO vo) {
        return QueryEmploymentCountResponse.builder()
                .totalStudentCount(vo.getTotalStudentCount())
                .passedCount(vo.getPassedCount())
                .approvedCount(vo.getApprovedCount())
                .build();
    }
}
