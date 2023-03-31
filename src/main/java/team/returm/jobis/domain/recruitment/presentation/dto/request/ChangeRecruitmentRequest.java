package team.returm.jobis.domain.recruitment.presentation.dto.request;

import java.util.List;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.returm.jobis.domain.recruitment.domain.enums.RecruitStatus;

@Getter
@NoArgsConstructor
public class ChangeRecruitmentRequest {
    @NotNull
    private List<UUID> recruitmentIds;

    @NotNull
    private RecruitStatus status;
}
