package team.retum.jobis.domain.company.presentation.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class UpdateMouWebRequest {
    @NotNull
    private List<Long> companyIds;
}
