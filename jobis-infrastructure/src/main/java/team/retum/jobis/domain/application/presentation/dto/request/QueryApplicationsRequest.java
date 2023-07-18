package team.retum.jobis.domain.application.presentation.dto.request;

import lombok.Builder;
import lombok.Getter;
import com.example.jobisapplication.domain.application.domain.ApplicationStatus;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Builder
public class QueryApplicationsRequest {

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
