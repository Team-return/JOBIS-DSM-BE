package team.returm.jobis.domain.application.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
public class ChangeFieldTrainDateRequest {

    @NotNull
    private List<Long> studentIds;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;
}
