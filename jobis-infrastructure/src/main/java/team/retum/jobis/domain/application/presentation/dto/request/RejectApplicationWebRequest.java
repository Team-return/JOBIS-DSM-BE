package team.retum.jobis.domain.application.presentation.dto.request;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class RejectApplicationWebRequest {

    @NotBlank
    @Size(min = 1, max = 100)
    private String reason;
}
