package team.returm.jobis.domain.application.presentation.dto.request;

import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.returm.jobis.domain.application.domain.enums.ApplicationStatus;

@Getter
@NoArgsConstructor
public class ChangeApplicationsStatusRequest {
    @NotNull
    private List<Long> applicationIdList;

    @NotNull
    private ApplicationStatus status;
}
