package team.retum.jobis.domain.wintern.model;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.common.annotation.Aggregate;

@Getter
@Builder(toBuilder = true)
@Aggregate
public class Wintern {

    private final boolean isWinterInterned;

    public Wintern toggle() {
        return this.toBuilder()
            .isWinterInterned(!this.isWinterInterned)
            .build();
    }
}
