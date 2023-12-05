package team.retum.jobis.domain.auth.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.global.util.RegexProperty;

@Getter
@NoArgsConstructor
public class VerifyAuthCodeWebRequest {

    @NotBlank
    @Pattern(regexp = RegexProperty.EMAIL)
    private String email;

    @NotBlank
    @Size(min = 6, max = 6)
    private String authCode;
}
