package team.retum.jobis.domain.review.model;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.common.annotation.Aggregate;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@Aggregate
public class Review {

    private final Long id;

    private final Long companyId;

    private final Long studentId;

    private final List<QnA> qnAS;

    private final LocalDateTime createdAt;

}
