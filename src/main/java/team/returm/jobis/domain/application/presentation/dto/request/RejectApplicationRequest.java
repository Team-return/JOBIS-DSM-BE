package team.returm.jobis.domain.application.presentation.dto.request;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class RejectApplicationRequest {

    @NotBlank
    private String reason;
}
