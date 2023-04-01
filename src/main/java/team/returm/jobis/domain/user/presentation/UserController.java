package team.returm.jobis.domain.user.presentation;

import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.returm.jobis.domain.user.presentation.dto.request.LoginRequest;
import team.returm.jobis.domain.user.presentation.dto.response.TokenResponse;
import team.returm.jobis.domain.user.presentation.dto.response.UserAuthResponse;
import team.returm.jobis.domain.user.service.LoginService;
import team.returm.jobis.domain.user.service.TokenReissueService;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {

    private final LoginService loginService;
    private final TokenReissueService tokenReissueService;

    @PostMapping("/login")
    public UserAuthResponse login(@RequestBody @Valid LoginRequest request) {
        return loginService.execute(request);
    }

    @PutMapping("/reissue")
    public TokenResponse reissue(@RequestHeader("X-Refresh-Token") String token) {
        return tokenReissueService.execute(token);
    }
}
