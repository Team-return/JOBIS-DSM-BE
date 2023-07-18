package team.retum.jobis.domain.user.presentation.dto.request;

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
}