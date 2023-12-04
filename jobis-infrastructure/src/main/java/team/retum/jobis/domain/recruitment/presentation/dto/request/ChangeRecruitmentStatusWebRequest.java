package team.retum.jobis.domain.recruitment.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.recruitment.dto.request.ChangeRecruitmentStatusRequest;
import team.retum.jobis.domain.recruitment.model.RecruitStatus;

import jakarta.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
public class ChangeRecruitmentStatusWebRequest {

    @NotNull
    private List<Long> recruitmentIds;

    @NotNull
    private RecruitStatus status;

    public ChangeRecruitmentStatusRequest toDomainRequest() {
        return ChangeRecruitmentStatusRequest.builder()
                .recruitmentIds(this.recruitmentIds)
                .status(this.status)
                .build();
    }
}
