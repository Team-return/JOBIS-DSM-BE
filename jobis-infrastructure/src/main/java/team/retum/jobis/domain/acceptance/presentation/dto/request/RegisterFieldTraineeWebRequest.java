package team.retum.jobis.domain.acceptance.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.global.annotation.ValidListElements;

import javax.validation.constraints.NotNull;
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
}
