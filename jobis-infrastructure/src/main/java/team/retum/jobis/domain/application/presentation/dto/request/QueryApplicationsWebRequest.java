package team.retum.jobis.domain.application.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.domain.application.model.ApplicationStatus;

@Getter
@Builder
public class QueryApplicationsWebRequest {

    @NotNull
    private final Long recruitmentId;

    @NotNull
    private final Long studentId;

    @NotNull
    private final ApplicationStatus applicationStatus;

    @NotBlank
    private final String studentName;

    @NotNull
    private Long companyId;
}
