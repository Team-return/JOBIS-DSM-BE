package team.retum.jobis.domain.student.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.student.dto.UpdateForgottenPasswordRequest;
import team.retum.jobis.global.util.RegexProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class UpdateForgottenPasswordWebRequest {

    @Size(max = 30)
    @NotBlank
    @Pattern(regexp = RegexProperty.EMAIL)
    private String email;

    @Pattern(regexp = RegexProperty.PASSWORD)
    private String password;

    public UpdateForgottenPasswordRequest toDomainRequest() {
        return UpdateForgottenPasswordRequest.builder()
                .email(this.email)
                .password(this.password)
                .build();
    }
}
