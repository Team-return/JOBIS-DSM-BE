package team.retum.jobis.domain.application.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.application.dto.request.ChangeFieldTrainDateRequest;
import team.retum.jobis.global.annotation.ValidListElements;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
public class ChangeFieldTrainDateWebRequest {

    @ValidListElements
    private List<Long> applicationIds;

    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    public ChangeFieldTrainDateRequest toDomainRequest() {
        return ChangeFieldTrainDateRequest.builder()
                .applicationIds(this.applicationIds)
                .startDate(this.startDate)
                .endDate(this.endDate)
                .build();
    }
}
