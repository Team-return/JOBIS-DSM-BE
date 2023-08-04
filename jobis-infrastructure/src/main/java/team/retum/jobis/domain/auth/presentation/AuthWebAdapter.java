package team.retum.jobis.domain.auth.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import team.retum.jobis.domain.auth.dto.TokenResponse;
import team.retum.jobis.domain.auth.presentation.dto.request.SendAuthCodeWebRequest;
import team.retum.jobis.domain.auth.usecase.SendAuthCodeUseCase;
import team.retum.jobis.domain.auth.usecase.TokenReissueUseCase;
import team.retum.jobis.domain.auth.usecase.VerifyAuthCodeUseCase;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthWebAdapter {

    private final SendAuthCodeUseCase sendAuthCodeUseCase;
    private final VerifyAuthCodeUseCase verifyAuthCodeUseCase;
    private final TokenReissueUseCase tokenReissueUseCase;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/code")
    public void sendSignUpCode(@RequestBody @Valid SendAuthCodeWebRequest request) {
        sendAuthCodeUseCase.execute(request.toDomainRequest());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/code")
    public void verifyCode(
            @RequestParam("email") String email,
            @RequestParam("auth_code") String authCode
    ) {
        verifyAuthCodeUseCase.execute(email, authCode);
    }

    @PutMapping("/reissue")
    public TokenResponse reissue(@RequestHeader("X-Refresh-Token") String token) {
        return tokenReissueUseCase.execute(token);
    }
}
