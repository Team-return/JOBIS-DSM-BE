package team.returm.jobis.domain.student.presentation;

import team.returm.jobis.domain.student.presentation.dto.request.SendAuthCodeRequest;
import team.returm.jobis.domain.student.presentation.dto.request.StudentSignUpRequest;
import team.returm.jobis.domain.student.presentation.dto.request.VerifyAuthCodeRequest;
import team.returm.jobis.domain.student.service.SendSignUpAuthCodeService;
import team.returm.jobis.domain.student.service.StudentSignUpService;
import team.returm.jobis.domain.student.service.VerifyAuthCodeService;
import team.returm.jobis.domain.user.presentation.dto.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import javax.validation.Valid;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/students")
@RestController
public class StudentController {

    private final SendSignUpAuthCodeService sendSignUpAuthCodeService;
    private final VerifyAuthCodeService verifyAuthCodeService;
    private final StudentSignUpService studentSignUpService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/code")
    public void sendCode(@RequestBody @Valid SendAuthCodeRequest request) {
        sendSignUpAuthCodeService.execute(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/code")
    public void verifyCode(@RequestBody @Valid VerifyAuthCodeRequest request) {
        verifyAuthCodeService.execute(request);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public TokenResponse signup(@RequestBody @Valid StudentSignUpRequest request) {
        return studentSignUpService.execute(request);
    }
}
