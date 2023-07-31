package team.retum.jobis.domain.user.presentation.dto.request;

import team.retum.jobis.domain.user.dto.LoginRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
public class LoginWebRequest {

    @NotBlank
    private String accountId;

    @NotBlank
    private String password;

    public LoginRequest toDomainRequest() {
        return LoginRequest.builder()
                .accountId(this.accountId)
                .password(this.password)
                .build();
    }
}