package team.retum.jobis.domain.intern.model;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.common.annotation.Aggregate;

@Getter
@Builder(toBuilder = true)
@Aggregate
public class WinterIntern {

    private final boolean isWinterInterned;

}
