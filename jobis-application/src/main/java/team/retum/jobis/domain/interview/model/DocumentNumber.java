package team.retum.jobis.domain.interview.model;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.common.annotation.Aggregate;

@Getter
@Builder
@Aggregate
public class DocumentNumber {

    private final Long id;

    private final String documentNumber;
}
