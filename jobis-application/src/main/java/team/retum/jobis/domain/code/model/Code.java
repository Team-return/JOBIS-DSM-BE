package team.retum.jobis.domain.code.model;

import team.retum.jobis.common.annotation.Aggregate;
import lombok.Builder;
import lombok.Getter;

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
