package team.returm.jobis.domain.student.presentation.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.returm.jobis.global.util.RegexProperty;

@Getter
@NoArgsConstructor
public class UpdatePasswordRequest {

    @NotBlank
    @Pattern(regexp = RegexProperty.EMAIL)
    private String email;

    @NotBlank
    @Pattern(regexp = RegexProperty.PASSWORD)
    private String password;
}
