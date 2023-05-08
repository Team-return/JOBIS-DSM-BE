package team.returm.jobis.domain.application.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import team.returm.jobis.domain.application.domain.enums.ApplicationStatus;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
public class ChangeApplicationsStatusRequest {

    @NotNull
    private List<Long> applicationIds;

    @NotNull
    private ApplicationStatus status;
}
