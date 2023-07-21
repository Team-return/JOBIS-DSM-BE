package team.retum.jobis.domain.user.presentation;

import com.example.jobisapplication.domain.auth.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.retum.jobis.domain.user.presentation.dto.request.LoginWebRequest;
import team.retum.jobis.domain.user.service.LoginService;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {

    private final LoginService loginService;

    @PostMapping("/login")
    public TokenResponse login(@RequestBody @Valid LoginWebRequest request) {
        return loginService.execute(request);
    }
}
