package team.retum.jobis.domain.code.model;

import team.retum.jobis.common.annotation.Aggregate;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Aggregate
public class RecruitAreaCode {

    private final Long recruitAreaId;

    private final Long codeId;

}
