package team.retum.jobis.domain.acceptance.presentation.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.acceptance.dto.request.ChangeContractDateRequest;
import team.retum.jobis.global.annotation.ValidListElements;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
public class ChangeContractDateWebRequest {

    @ValidListElements
    private List<Long> acceptanceIds;

    @NotNull
    @Future
    private LocalDate contractDate;

    public ChangeContractDateRequest toDomainRequest() {
        return ChangeContractDateRequest.builder()
                .acceptanceIds(this.acceptanceIds)
                .contractDate(this.contractDate)
                .build();
    }
}
