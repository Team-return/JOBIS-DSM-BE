package team.returm.jobis.domain.application.presentation.dto.request;

import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RegisterFieldTraineeRequest {
    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;
}
