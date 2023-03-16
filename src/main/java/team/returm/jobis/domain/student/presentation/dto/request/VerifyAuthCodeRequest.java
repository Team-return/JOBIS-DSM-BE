package team.returm.jobis.domain.student.presentation.dto.request;

import team.returm.jobis.global.util.RegexProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class VerifyAuthCodeRequest {

    @Pattern(regexp = RegexProperty.EMAIL)
    private String email;

    @NotBlank
    @Size(min = 6, max = 6)
    private String authCode;
}
