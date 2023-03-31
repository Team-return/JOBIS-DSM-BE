package team.returm.jobis.domain.auth.presentation.dto.request;

import javax.validation.constraints.Pattern;
import team.returm.jobis.domain.auth.domain.types.AuthCodeType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import team.returm.jobis.global.util.RegexProperty;

@Getter
@NoArgsConstructor
public class SendAuthCodeRequest {

    @NotBlank
    @Pattern(regexp = RegexProperty.EMAIL)
    private String email;

    @NotNull
    private AuthCodeType authCodeType;

    @NotBlank
    private String userName;
}
