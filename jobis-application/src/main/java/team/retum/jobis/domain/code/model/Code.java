package team.retum.jobis.domain.code.model;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.common.annotation.Aggregate;

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

    public Code changeAccessible(boolean isPublic) {
        return this.toBuilder()
                .isPublic(isPublic)
                .build();
    }
}
