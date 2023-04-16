package team.returm.jobis.domain.user.presentation.dto.request;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginRequest {

    @NotBlank
    private String accountId;

    @NotBlank
    private String password;
}