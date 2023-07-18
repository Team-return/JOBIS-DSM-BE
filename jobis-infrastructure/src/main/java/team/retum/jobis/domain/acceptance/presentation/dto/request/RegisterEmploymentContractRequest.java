package team.retum.jobis.domain.acceptance.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
public class RegisterEmploymentContractRequest {

    @NotNull
    private List<String> codeKeywords;

    @NotNull
    private List<Long> applicationIds;
}
