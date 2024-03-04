package team.retum.jobis.domain.acceptance.presentation.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.acceptance.dto.request.RegisterFieldTraineeRequest;
import team.retum.jobis.global.annotation.ValidListElements;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
public class RegisterFieldTraineeWebRequest {

    @ValidListElements
    private List<Long> applicationIds;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    public RegisterFieldTraineeRequest toDomainRequest() {
        return new RegisterFieldTraineeRequest(applicationIds, startDate, endDate);
    }
}
