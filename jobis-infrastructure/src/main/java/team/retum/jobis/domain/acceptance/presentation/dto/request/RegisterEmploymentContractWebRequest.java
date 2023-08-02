package team.retum.jobis.domain.acceptance.presentation.dto.request;

import team.retum.jobis.domain.acceptance.dto.request.RegisterEmploymentContractRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.global.annotation.ValidListElements;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
public class RegisterEmploymentContractWebRequest {

    @ValidListElements
    private List<String> codeKeywords;

    @ValidListElements
    private List<Long> applicationIds;

    public RegisterEmploymentContractRequest toDomainRequest() {
        return RegisterEmploymentContractRequest.builder()
                .codeKeywords(this.codeKeywords)
                .applicationIds(this.applicationIds)
                .build();
    }
}
