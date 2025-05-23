package team.retum.jobis.domain.auth.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.auth.model.AuthCodeType;
import team.retum.jobis.global.util.RegexProperty;

@Getter
@NoArgsConstructor
public class SendAuthCodeWebRequest {

    @NotBlank
    @Pattern(regexp = RegexProperty.STUDENT_EMAIL)
    private String email;

    @NotNull
    private AuthCodeType authCodeType;
}
