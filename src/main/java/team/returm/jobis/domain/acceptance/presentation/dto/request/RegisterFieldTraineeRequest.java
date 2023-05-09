package team.returm.jobis.domain.acceptance.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class RegisterFieldTraineeRequest {

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;
}
