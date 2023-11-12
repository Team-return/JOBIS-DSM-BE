package team.retum.jobis.domain.application.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.application.model.ApplicationStatus;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
public class ChangeApplicationsStatusWebRequest {

    @NotNull
    private List<@NotNull Long> applicationIds;

    @NotNull
    private ApplicationStatus status;
}
