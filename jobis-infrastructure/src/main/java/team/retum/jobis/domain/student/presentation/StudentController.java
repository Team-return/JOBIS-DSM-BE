package team.retum.jobis.domain.student.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import team.retum.jobis.domain.student.presentation.dto.request.StudentSignUpRequest;
import team.retum.jobis.domain.student.presentation.dto.request.UpdateForgottenPasswordRequest;
import team.retum.jobis.domain.student.presentation.dto.request.UpdatePasswordRequest;
import team.retum.jobis.domain.student.presentation.dto.request.UpdateStudentProfileRequest;
import team.retum.jobis.domain.student.presentation.dto.response.StudentMyPageResponse;
import team.retum.jobis.domain.student.service.CheckStudentPasswordService;
import team.retum.jobis.domain.student.service.StudentMyPageService;
import team.retum.jobis.domain.student.service.StudentSignUpService;
import team.retum.jobis.domain.student.service.UpdateStudentForgottenPasswordService;
import team.retum.jobis.domain.student.service.UpdateStudentPasswordService;
import team.retum.jobis.domain.student.service.UpdateStudentProfileService;
import team.retum.jobis.domain.student.service.VerifyStudentService;
import team.retum.jobis.domain.user.presentation.dto.response.TokenResponse;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/students")
@RestController
public class StudentController {

    private final StudentSignUpService studentSignUpService;
    private final UpdateStudentForgottenPasswordService updateStudentForgottenPasswordService;
    private final StudentMyPageService studentMyPageService;
    private final VerifyStudentService verifyStudentService;
    private final UpdateStudentProfileService updateStudentProfileService;
    private final CheckStudentPasswordService checkStudentPasswordService;
    private final UpdateStudentPasswordService updateStudentPasswordService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public TokenResponse signup(@RequestBody @Valid StudentSignUpRequest request) {
        return studentSignUpService.execute(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/forgotten_password")
    public void updateForgottenPassword(@RequestBody @Valid UpdateForgottenPasswordRequest request) {
        updateStudentForgottenPasswordService.execute(request);
    }

    @GetMapping("/my")
    public StudentMyPageResponse myPage() {
        return studentMyPageService.execute();
    }

    @GetMapping("/exists")
    public void checkStudentExists(
            @RequestParam("gcn") String gcn,
            @RequestParam("name") String name
    ) {
        verifyStudentService.execute(gcn, name);
    }

    @PatchMapping("/profile")
    public void updateStudentProfile(@RequestBody @Valid UpdateStudentProfileRequest request) {
        updateStudentProfileService.execute(request.getProfileImageUrl());
    }

    @GetMapping("/password")
    public void checkStudentPassword(@RequestParam(value = "password") String password) {
        checkStudentPasswordService.execute(password);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/password")
    public void updatePassword(@RequestBody @Valid UpdatePasswordRequest request) {
        updateStudentPasswordService.execute(request);
    }
}
