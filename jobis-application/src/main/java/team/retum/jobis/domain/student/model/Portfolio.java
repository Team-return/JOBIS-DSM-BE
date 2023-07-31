package team.retum.jobis.domain.student.model;

import team.retum.jobis.common.annotation.Aggregate;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Aggregate
public class Portfolio {

    private final Long id;

    private final String portfolioUrl;

    private final Long studentId;

}
