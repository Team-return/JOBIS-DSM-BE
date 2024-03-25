package team.retum.jobis.domain.code.model;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.common.annotation.Aggregate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static team.retum.jobis.domain.code.model.CodeType.JOB;
import static team.retum.jobis.domain.code.model.CodeType.TECH;

@Getter
@Builder(toBuilder = true)
@Aggregate
public class Code {

    private final Long id;

    private final CodeType codeType;

    private final JobType jobType;

    private final String keyword;

    private final boolean isPublic;

    private final Long parentCodeId;

    public static Map<CodeType, List<Long>> combineCodesWithType(List<Long> jobCode, List<Long> techCode) {
        Map<CodeType, List<Long>> codeIds = new HashMap<>();
        codeIds.put(JOB, jobCode);
        codeIds.put(TECH, techCode);

        return codeIds;
    }

    public Code changeAccessible(boolean isPublic) {
        return this.toBuilder()
            .isPublic(isPublic)
            .build();
    }
}
