package team.retum.jobis.domain.recruitment.dto.request;

import lombok.Builder;

import java.util.List;

@Builder
public record CreateRecruitAreaRequest(
    List<Long> jobCodes,
    List<Long> techCodes,
    int hiring,
    String majorTask,
    String preferentialTreatment
) {

}
