package team.returm.jobis.domain.application.presentation.dto.request;

import team.returm.jobis.domain.application.domain.enums.ApplicationStatus;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Getter
@Builder
public class QueryApplicationsRequest {

    @NotNull
    private final UUID recruitmentId;
    @NotNull
    private final UUID studentId;
    @NotNull
    private final ApplicationStatus applicationStatus;
    @NotBlank
    private final String studentName;
}
