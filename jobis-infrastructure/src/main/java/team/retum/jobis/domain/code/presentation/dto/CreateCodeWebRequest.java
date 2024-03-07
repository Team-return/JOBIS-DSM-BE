package team.retum.jobis.domain.code.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.code.dto.request.CreateCodeRequest;
import team.retum.jobis.domain.code.model.CodeType;
import team.retum.jobis.domain.code.model.JobType;

@Getter
@NoArgsConstructor
public class CreateCodeWebRequest {

    @NotNull
    private CodeType codeType;

    private JobType jobType;

    @NotBlank
    @Size(max = 30)
    private String keyword;

    public CreateCodeRequest toDomainRequest() {
        return new CreateCodeRequest(codeType, jobType, keyword);
    }
}
