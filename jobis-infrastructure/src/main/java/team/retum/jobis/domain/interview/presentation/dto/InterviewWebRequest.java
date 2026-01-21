package team.retum.jobis.domain.interview.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import team.retum.jobis.domain.interview.dto.request.InterviewRequest;
import team.retum.jobis.domain.recruitment.model.ProgressType;
import team.retum.jobis.global.exception.BadRequestException;
import team.retum.jobis.global.util.RegexProperty;

import java.time.LocalDate;
import java.util.regex.Pattern;

public record InterviewWebRequest(

    @NotNull
    ProgressType interviewType,

    @NotNull
    LocalDate startDate,

    LocalDate endDate,

    @NotBlank
    String interviewTime,

    @NotBlank
    @Size(max = 20)
    String companyName,

    @NotBlank
    @Size(max = 80)
    String location,

    Long studentId,

    Long documentNumberId
) {
    public InterviewRequest toDomainRequest() {
        if (!Pattern.matches(RegexProperty.INTERVIEW_TIME, this.interviewTime)) {
            throw BadRequestException.EXCEPTION;
        }

        return InterviewRequest.builder()
            .interviewType(this.interviewType)
            .startDate(this.startDate)
            .endDate(this.endDate)
            .interviewTime(this.interviewTime)
            .companyName(this.companyName)
            .location(this.location)
            .studentId(this.studentId)
            .documentNumberId(this.documentNumberId)
            .build();
    }
}
