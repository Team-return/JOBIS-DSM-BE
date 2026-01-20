package team.retum.jobis.domain.interview.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DocumentNumberWebRequest(
    @NotBlank
    @Size(max = 6)
    String documentNumber
) {
}
