package team.retum.jobis.domain.application.dto;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.domain.application.model.ApplicationStatus;

import java.time.Year;

@Getter
@Builder
public class ApplicationFilter {

    private final Long recruitmentId;
    private final Long studentId;
    private final ApplicationStatus applicationStatus;
    private final String studentName;
    private final Boolean winterIntern;
    private final Year year;
    private final Long page;
    @Builder.Default
    private int limit = 10;

    public Long getOffset() {
        return (page - 1) * limit;
    }
}
