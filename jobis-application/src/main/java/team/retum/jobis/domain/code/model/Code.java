package team.retum.jobis.domain.code.model;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.common.annotation.Aggregate;

@Getter
@Builder
@Aggregate
public class Code {

    private final Long id;

    private final CodeType codeType;

    private final JobType jobType;

    private final String keyword;

    private final Long parentCodeId;
}
