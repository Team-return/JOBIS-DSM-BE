package team.retum.jobis.domain.interest.model;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.common.annotation.Aggregate;

@Getter
@Builder(toBuilder = true)
@Aggregate
public class Interest {

    private final Long id;

    private final Long studentId;

    private final Long code;
}
