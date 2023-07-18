package team.retum.jobis.domain.acceptance.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
public class ChangeContractDateRequest {

    @NotNull
    private List<Long> acceptanceIds;

    @NotNull
    private LocalDate contractDate;

}
