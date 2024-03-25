package team.retum.jobis.domain.user.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.auth.model.PlatformType;
import team.retum.jobis.domain.user.dto.request.LoginRequest;
import team.retum.jobis.global.util.RegexProperty;

@Getter
@NoArgsConstructor
public class LoginWebRequest {

    @Size(max = 30)
    @NotBlank
    private String accountId;

    @Pattern(regexp = RegexProperty.PASSWORD)
    private String password;

    @NotNull
    private PlatformType platformType;

    private String deviceToken;

    public LoginRequest toDomainRequest() {
        return new LoginRequest(accountId, password, platformType, deviceToken);
    }
}
