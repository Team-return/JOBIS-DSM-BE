package team.returm.jobis.domain.acceptance.presentation.dto.request;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RegisterEmploymentContractRequest {

    @NotBlank
    private String studentGcn;

    @NotBlank
    private String studentName;

    @NotNull
    private Long companyId;

    @NotNull
    private List<String> codeKeywords;

    @NotNull
    private List<Long> applicationIds;
}
