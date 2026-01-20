package team.retum.jobis.domain.interview.dto.request;

import lombok.Builder;
import team.retum.jobis.domain.recruitment.model.ProgressType;

import java.time.LocalDate;

@Builder
public record InterviewRequest(
    ProgressType interviewType,
    LocalDate startDate,
    LocalDate endDate,
    String interviewTime,
    String companyName,
    String location,
    Long studentId,
    Long documentNumberId
) {
}
