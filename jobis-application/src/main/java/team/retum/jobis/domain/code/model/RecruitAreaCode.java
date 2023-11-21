package team.retum.jobis.domain.code.model;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.common.annotation.Aggregate;

@Getter
@Builder
@Aggregate
public class RecruitAreaCode {

    private final Long recruitAreaId;

    private final Long code;

    private final CodeType type;

}
