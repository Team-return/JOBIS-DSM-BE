package team.returm.jobis.domain.student.presentation;

import team.returm.jobis.domain.student.presentation.dto.request.StudentSignUpRequest;
import team.returm.jobis.domain.student.presentation.dto.request.UpdatePasswordRequest;
import team.returm.jobis.domain.student.service.StudentSignUpService;
import team.returm.jobis.domain.student.service.UpdatePasswordService;
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

    private final StudentSignUpService studentSignUpService;
    private final UpdatePasswordService updatePasswordService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public TokenResponse signup(@RequestBody @Valid StudentSignUpRequest request) {
        return studentSignUpService.execute(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/password")
    public void updatePassword(@RequestBody @Valid UpdatePasswordRequest request) {
        updatePasswordService.execute(request);
    }
}
