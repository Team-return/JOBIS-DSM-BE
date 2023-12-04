package team.retum.jobis.domain.company.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.company.dto.request.UpdateMouRequest;

import jakarta.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
public class UpdateMouWebRequest {

    @NotNull
    private List<Long> companyIds;

    public UpdateMouRequest toDomainRequest() {
        return new UpdateMouRequest(this.companyIds);
    }
}
