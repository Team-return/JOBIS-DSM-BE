package team.retum.jobis.domain.recruitment.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.recruitment.domain.enums.RecruitStatus;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
public class ChangeRecruitmentRequest {
    @NotNull
    private List<Long> recruitmentIds;

    @NotNull
    private RecruitStatus status;
}
