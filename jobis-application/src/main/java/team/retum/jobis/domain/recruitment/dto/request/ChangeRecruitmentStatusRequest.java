package team.retum.jobis.domain.recruitment.dto.request;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.domain.recruitment.model.RecruitStatus;

import java.util.List;

@Getter
@Builder
public class ChangeRecruitmentStatusRequest {

    private List<Long> recruitmentIds;

    private RecruitStatus status;
}
