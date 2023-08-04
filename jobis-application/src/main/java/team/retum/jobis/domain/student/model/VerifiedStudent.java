package team.retum.jobis.domain.student.model;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.common.annotation.Aggregate;

@Getter
@Builder
@Aggregate
public class VerifiedStudent {

    private final String gcn;

    private final String name;

}
