package team.retum.jobis.domain.recruitment.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.recruitment.dto.request.CreateRecruitAreaRequest;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@NoArgsConstructor
public class RecruitAreaWebRequest {

    @NotNull
    private List<@NotNull Long> jobCodes;

    @NotNull
    private List<@NotNull Long> techCodes;

    @NotNull
    private int hiring;

    @Size(max = 200)
    @NotBlank
    private String majorTask;

    public CreateRecruitAreaRequest toDomainRequest() {
        return CreateRecruitAreaRequest.builder()
                .hiring(this.hiring)
                .jobCodes(this.jobCodes)
                .techCodes(this.techCodes)
                .majorTask(this.majorTask)
                .build();
    }


}