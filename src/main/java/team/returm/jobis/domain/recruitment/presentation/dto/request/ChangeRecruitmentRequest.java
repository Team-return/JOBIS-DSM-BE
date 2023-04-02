package team.returm.jobis.domain.recruitment.presentation.dto.request;

import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.returm.jobis.domain.recruitment.domain.enums.RecruitStatus;

@Getter
@NoArgsConstructor
public class ChangeRecruitmentRequest {
    @NotNull
    private List<Long> recruitmentIds;

    @NotNull
    private RecruitStatus status;
}
