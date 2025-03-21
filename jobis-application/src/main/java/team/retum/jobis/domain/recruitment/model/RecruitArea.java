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

    private static final long JOB_CODE = 1L;

    private static final long TECH_CODE = 19L;

    public static RecruitArea of(CreateRecruitAreaRequest request, Long recruitmentId) {
        return RecruitArea.builder()
            .recruitmentId(recruitmentId)
            .hiredCount(request.hiring())
            .majorTask(request.majorTask())
            .preferentialTreatment(request.preferentialTreatment())
            .codes(Code.combineCodesWithType(request.jobCodes(), request.techCodes()))
            .build();
    }

    public static RecruitArea of(Long recruitmentId) {
        return RecruitArea.builder()
                .recruitmentId(recruitmentId)
                .hiredCount(1)
                .majorTask("N/A")
                .preferentialTreatment("N/A")
                .codes(Code.combineCodesWithType(List.of(JOB_CODE), List.of(TECH_CODE)))
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
