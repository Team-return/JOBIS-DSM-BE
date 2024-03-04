package team.retum.jobis.domain.user.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.retum.jobis.domain.auth.dto.response.TokenResponse;
import team.retum.jobis.domain.user.presentation.dto.request.LoginWebRequest;
import team.retum.jobis.domain.user.usecase.LoginUseCase;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserWebAdapter {

    private final LoginUseCase loginUseCase;

    @PostMapping("/login")
    public TokenResponse login(@RequestBody @Valid LoginWebRequest webRequest) {
        return loginUseCase.execute(webRequest.toDomainRequest());
    }
}
