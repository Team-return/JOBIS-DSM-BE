package team.retum.jobis.domain.student.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.student.dto.UpdateForgottenPasswordRequest;
import team.retum.jobis.global.util.RegexProperty;

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
