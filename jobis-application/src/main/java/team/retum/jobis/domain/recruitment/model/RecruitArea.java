package team.retum.jobis.domain.recruitment.model;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.common.annotation.Aggregate;
import team.retum.jobis.domain.code.model.Code;
import team.retum.jobis.domain.code.model.CodeType;
import team.retum.jobis.domain.recruitment.dto.request.CreateRecruitAreaRequest;

import java.util.List;
import java.util.Map;

@Getter
@Builder(toBuilder = true)
@Aggregate
public class RecruitArea {

    private final Long id;

    private final Integer hiredCount;

    private final String majorTask;

    private final String preferentialTreatment;

    private final Long recruitmentId;

    private final Map<CodeType, List<Long>> codes;

    public static RecruitArea of(CreateRecruitAreaRequest request, Long recruitmentId) {
        return RecruitArea.builder()
            .recruitmentId(recruitmentId)
            .hiredCount(request.hiring())
            .majorTask(request.majorTask())
            .preferentialTreatment(request.preferentialTreatment())
            .codes(Code.combineCodesWithType(request.jobCodes(), request.techCodes()))
            .build();
    }

    public RecruitArea update(CreateRecruitAreaRequest request) {
        return this.toBuilder()
            .hiredCount(request.hiring())
            .majorTask(request.majorTask())
            .preferentialTreatment(request.preferentialTreatment())
            .codes(Code.combineCodesWithType(request.jobCodes(), request.techCodes()))
            .build();
    }

}
