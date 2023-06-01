package team.returm.jobis.domain.acceptance.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import team.returm.jobis.global.annotation.ValidListElements;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
public class RegisterFieldTraineeRequest {

    @ValidListElements
    private List<Long> applicationIds;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;
}
