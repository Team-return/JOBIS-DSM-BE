package team.retum.jobis.domain.interview.dto;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.domain.recruitment.model.ProgressType;

@Getter
@Builder
public class InterviewFilter {

    private final Integer year;

    private final Integer month;

    private final String companyName;

    private final ProgressType interviewType;

    private final Long studentId;
}
