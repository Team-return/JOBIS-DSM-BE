package team.returm.jobis.domain.user.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.returm.jobis.domain.auth.service.TokenReissueService;
import team.returm.jobis.domain.user.presentation.dto.request.LoginRequest;
import team.returm.jobis.domain.user.presentation.dto.response.TokenResponse;
import team.returm.jobis.domain.user.service.LoginService;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {

    private final LoginService loginService;

    @PostMapping("/login")
    public TokenResponse login(@RequestBody @Valid LoginRequest request) {
        return loginService.execute(request);
    }
}
