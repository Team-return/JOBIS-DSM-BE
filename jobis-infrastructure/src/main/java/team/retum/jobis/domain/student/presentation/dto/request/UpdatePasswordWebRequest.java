package team.retum.jobis.domain.student.presentation.dto.request;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.global.util.RegexProperty;

@Getter
@NoArgsConstructor
public class UpdatePasswordWebRequest {

    @Pattern(regexp = RegexProperty.PASSWORD)
    private String currentPassword;

    @Pattern(regexp = RegexProperty.PASSWORD)
    private String newPassword;
}
