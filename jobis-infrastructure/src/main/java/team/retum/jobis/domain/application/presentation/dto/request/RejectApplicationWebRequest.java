package team.retum.jobis.domain.application.presentation.dto.request;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class RejectApplicationWebRequest {

    @NotBlank
    private String reason;
}
