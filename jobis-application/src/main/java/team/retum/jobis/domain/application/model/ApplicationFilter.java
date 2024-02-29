package team.retum.jobis.domain.application.model;

import lombok.Builder;
import lombok.Getter;

import java.time.Year;

@Getter
@Builder
public class ApplicationFilter {
    private final Long recruitmentId;
    private final Long studentId;
    private final ApplicationStatus applicationStatus;
    private final String studentName;
    private final Year year;
}
