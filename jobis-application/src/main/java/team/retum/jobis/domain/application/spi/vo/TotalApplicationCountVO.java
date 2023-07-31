package team.retum.jobis.domain.application.spi.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class TotalApplicationCountVO {
    private final Long totalStudentCount;
    private final Long passedCount;
    private final Long approvedCount;
}
