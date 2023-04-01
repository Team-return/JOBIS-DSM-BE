package team.returm.jobis.domain.user.presentation.dto.request;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginRequest {

    @NotBlank(message = "사업자번호는 널 또는 공백을 포함할 수 없습니다.")
    private String accountId;

    @NotBlank(message = "비밀번호는 Null, 공백, 띄어쓰기를 허용하지 않습니다.")
    private String password;
}