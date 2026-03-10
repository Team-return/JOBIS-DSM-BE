package team.retum.jobis.domain.interview.model;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.common.annotation.Aggregate;
import team.retum.jobis.domain.recruitment.model.ProgressType;

import java.time.LocalDate;

@Getter
@Aggregate
@Builder(toBuilder = true)
public class Interview {

    private final Long id;

    private final ProgressType interviewType;

    private final LocalDate startDate;

    private final LocalDate endDate;

    private final String interviewTime;

    private final String companyName;

    private final String location;

    private final Long studentId;

    private final Long documentNumberId;
}
