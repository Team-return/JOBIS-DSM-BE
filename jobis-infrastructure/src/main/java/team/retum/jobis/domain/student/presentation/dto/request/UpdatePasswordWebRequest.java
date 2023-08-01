package team.retum.jobis.domain.student.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.student.dto.UpdatePasswordRequest;
import team.retum.jobis.global.util.RegexProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
public class UpdatePasswordWebRequest {

    @NotBlank
    @Pattern(regexp = RegexProperty.PASSWORD)
    private String currentPassword;

    @NotBlank
    @Pattern(regexp = RegexProperty.PASSWORD)
    private String newPassword;

    public UpdatePasswordRequest toDomainRequest() {
        return UpdatePasswordRequest.builder()
                .currentPassword(this.currentPassword)
                .newPassword(this.newPassword)
                .build();
    }
}
