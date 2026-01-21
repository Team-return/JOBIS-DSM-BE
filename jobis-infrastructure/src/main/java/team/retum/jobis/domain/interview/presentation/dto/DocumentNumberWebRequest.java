package team.retum.jobis.domain.interview.presentation.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record DocumentNumberWebRequest(
    @NotBlank
    String documentNumber,

    List<Long> interviewIds
) {
}
