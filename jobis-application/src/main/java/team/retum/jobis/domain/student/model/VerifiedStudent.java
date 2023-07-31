package team.retum.jobis.domain.student.model;

import team.retum.jobis.common.annotation.Aggregate;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Aggregate
public class VerifiedStudent {

    private final String gcn;

    private final String name;

}
