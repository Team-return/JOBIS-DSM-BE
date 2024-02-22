package team.retum.jobis.domain.recruitment.presentation.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.recruitment.model.RecruitStatus;

import java.util.List;

@Getter
@NoArgsConstructor
public class ChangeRecruitmentStatusWebRequest {

    @NotNull
    private List<Long> recruitmentIds;

    @NotNull
    private RecruitStatus status;
}
