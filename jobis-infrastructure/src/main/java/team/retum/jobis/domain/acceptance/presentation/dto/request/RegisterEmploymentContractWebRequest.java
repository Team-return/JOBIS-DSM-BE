package team.retum.jobis.domain.acceptance.presentation.dto.request;

import com.example.jobisapplication.domain.acceptance.dto.request.RegisterEmploymentContractRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
public class RegisterEmploymentContractWebRequest {

    @NotNull
    private List<String> codeKeywords;

    @NotNull
    private List<Long> applicationIds;

    public RegisterEmploymentContractRequest toDomainRequest() {
        return RegisterEmploymentContractRequest.builder()
                .codeKeywords(this.codeKeywords)
                .applicationIds(this.applicationIds)
                .build();
    }
}
