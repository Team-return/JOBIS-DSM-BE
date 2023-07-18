package team.retum.jobis.domain.company.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
public class UpdateMouWebRequest {

    @NotNull
    private List<Long> companyIds;
}