package team.retum.jobis.domain.user.presentation.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.user.dto.LoginRequest;
import team.retum.jobis.global.util.RegexProperty;

@Getter
@NoArgsConstructor
public class LoginWebRequest {

    @Size(max = 30)
    @NotBlank
    private String accountId;

    @Pattern(regexp = RegexProperty.PASSWORD)
    private String password;

    public LoginRequest toDomainRequest() {
        return LoginRequest.builder()
                .accountId(this.accountId)
                .password(this.password)
                .build();
    }
}