package team.returm.jobis.domain.auth.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import team.returm.jobis.domain.auth.service.SendAuthCodeService;
import team.returm.jobis.domain.auth.service.VerifyAuthCodeService;
import team.returm.jobis.domain.auth.presentation.dto.request.SendAuthCodeRequest;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {

    private final SendAuthCodeService sendAuthCodeService;
    private final VerifyAuthCodeService verifyAuthCodeService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/code")
    public void sendSignUpCode(@RequestBody @Valid SendAuthCodeRequest request) {
        sendAuthCodeService.execute(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @GetMapping("/code")
    public void verifyCode(@RequestParam("email") String email, @RequestParam("auth-code") String authCode) {
        verifyAuthCodeService.execute(email, authCode);
    }
}
