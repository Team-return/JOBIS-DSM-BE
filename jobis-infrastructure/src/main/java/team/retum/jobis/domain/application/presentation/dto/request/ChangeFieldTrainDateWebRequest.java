package team.retum.jobis.domain.application.presentation.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.application.dto.request.ChangeFieldTrainDateRequest;
import team.retum.jobis.global.annotation.ValidListElements;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
public class ChangeFieldTrainDateWebRequest {

    @ValidListElements
    private List<Long> applicationIds;

    @NotNull
    @Future
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    public ChangeFieldTrainDateRequest toDomainRequest() {
        return new ChangeFieldTrainDateRequest(applicationIds, startDate, endDate);
    }
}
