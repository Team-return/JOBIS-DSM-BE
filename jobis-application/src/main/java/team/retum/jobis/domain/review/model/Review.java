package team.retum.jobis.domain.review.model;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.common.annotation.Aggregate;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@Aggregate
public class Review {

    private final String id;

    private final Long companyId;

    private final List<QnAElement> qnAElements;

    private final String studentName;

    private final Integer year;

    private final LocalDate createdDate;

}
