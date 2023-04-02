package team.returm.jobis.domain.recruitment.presentation.dto.request;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateRecruitAreaRequest {

    @NotNull
    private List<Long> jobCodes;
    @NotNull
    private List<Long> techCodes;
    @NotNull
    private int hiring;
    @NotBlank
    private String majorTask;
}
