package team.retum.jobis.domain.student.model;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.common.annotation.Aggregate;

@Getter
@Builder
@Aggregate
public class Portfolio {

    private final Long id;

    private final String portfolioUrl;

    private final Long studentId;

}
