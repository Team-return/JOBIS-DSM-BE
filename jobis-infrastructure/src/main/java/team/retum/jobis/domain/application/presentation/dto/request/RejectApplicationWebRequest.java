package team.retum.jobis.domain.application.presentation.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RejectApplicationWebRequest {

    @NotBlank
    @Size(min = 1, max = 100)
    private String reason;
}
