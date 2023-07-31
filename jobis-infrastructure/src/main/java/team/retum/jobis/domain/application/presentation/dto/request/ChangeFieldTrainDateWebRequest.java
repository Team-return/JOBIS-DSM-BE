package team.retum.jobis.domain.application.presentation.dto.request;

import com.example.jobisapplication.domain.application.dto.request.ChangeFieldTrainDateRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
public class ChangeFieldTrainDateWebRequest {

    @NotNull
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
