package team.retum.jobis.domain.recruitment.model;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.common.annotation.Aggregate;
import team.retum.jobis.domain.code.model.CodeType;
import team.retum.jobis.domain.recruitment.dto.request.CreateRecruitAreaRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static team.retum.jobis.domain.code.model.CodeType.JOB;
import static team.retum.jobis.domain.code.model.CodeType.TECH;

@Getter
@Builder
@Aggregate
public class RecruitArea {

    private final Long id;

    private final Integer hiredCount;

    private final String majorTask;

    private final String preferentialTreatment;

    private final Long recruitmentId;

    private final Map<CodeType, List<Long>> codes;

    public static RecruitArea of(CreateRecruitAreaRequest request, Long recruitmentId) {
        Map<CodeType, List<Long>> codeIds = new HashMap<>();
        codeIds.put(JOB, request.getJobCodes());
        codeIds.put(TECH, request.getTechCodes());
        return RecruitArea.builder()
                .recruitmentId(recruitmentId)
                .hiredCount(request.getHiring())
                .majorTask(request.getMajorTask())
                .preferentialTreatment(request.getPreferentialTreatment())
                .codes(codeIds)
                .build();
    }

}
