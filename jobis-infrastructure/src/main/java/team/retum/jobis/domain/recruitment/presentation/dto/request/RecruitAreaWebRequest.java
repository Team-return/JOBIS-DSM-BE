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

    @Size(max = 3500)
    @NotBlank
    private String majorTask;

    @Size(max = 500)
    private String preferentialTreatment;

    public CreateRecruitAreaRequest toDomainRequest() {
        return CreateRecruitAreaRequest.builder()
                .hiring(this.hiring)
                .jobCodes(this.jobCodes)
                .techCodes(this.techCodes)
                .majorTask(this.majorTask)
                .preferentialTreatment(preferentialTreatment)
                .build();
    }
}