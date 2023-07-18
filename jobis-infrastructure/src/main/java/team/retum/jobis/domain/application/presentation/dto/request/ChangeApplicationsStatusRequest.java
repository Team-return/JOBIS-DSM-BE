package team.retum.jobis.domain.application.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import com.example.jobisapplication.domain.application.domain.ApplicationStatus;

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
