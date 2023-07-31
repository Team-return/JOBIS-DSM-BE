package team.retum.jobis.domain.recruitment.dto.request;

import team.retum.jobis.domain.recruitment.model.RecruitStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ChangeRecruitmentStatusRequest {

    private List<Long> recruitmentIds;

    private RecruitStatus status;
}
