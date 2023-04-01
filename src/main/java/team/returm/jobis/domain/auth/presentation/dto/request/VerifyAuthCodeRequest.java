package team.returm.jobis.domain.auth.presentation.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.returm.jobis.global.util.RegexProperty;

@Getter
@NoArgsConstructor
public class VerifyAuthCodeRequest {

    @NotBlank
    @Pattern(regexp = RegexProperty.EMAIL)
    private String email;

    @NotBlank
    @Size(min = 6, max = 6)
    private String authCode;
}
