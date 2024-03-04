package team.retum.jobis.domain.user.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import team.retum.jobis.domain.auth.dto.TokenResponse;
import team.retum.jobis.domain.user.presentation.dto.request.LoginWebRequest;
import team.retum.jobis.domain.user.presentation.dto.request.SetDeviceTokenWebRequest;
import team.retum.jobis.domain.user.usecase.LoginUseCase;
import team.retum.jobis.domain.user.usecase.SetDeviceTokenUseCase;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserWebAdapter {

    private final LoginUseCase loginUseCase;
    private final SetDeviceTokenUseCase setDeviceTokenUseCase;

    @PostMapping("/login")
    public TokenResponse login(@RequestBody @Valid LoginWebRequest request) {
        return loginUseCase.execute(request.toDomainRequest());
    }

    @PatchMapping("/token")
    public void setToken(@RequestBody @Valid SetDeviceTokenWebRequest request) {
        setDeviceTokenUseCase.execute(request.getToken());
    }
}
