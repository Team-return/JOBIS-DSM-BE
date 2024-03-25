package team.retum.jobis.domain.student.presentation;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import team.retum.jobis.domain.auth.dto.response.TokenResponse;
import team.retum.jobis.domain.student.dto.response.StudentMyPageResponse;
import team.retum.jobis.domain.student.presentation.dto.request.StudentSignUpWebRequest;
import team.retum.jobis.domain.student.presentation.dto.request.UpdateForgottenPasswordWebRequest;
import team.retum.jobis.domain.student.presentation.dto.request.UpdatePasswordWebRequest;
import team.retum.jobis.domain.student.presentation.dto.request.UpdateStudentProfileWebRequest;
import team.retum.jobis.domain.student.usecase.CheckStudentPasswordUseCase;
import team.retum.jobis.domain.student.usecase.StudentMyPageUseCase;
import team.retum.jobis.domain.student.usecase.StudentSignUpUseCase;
import team.retum.jobis.domain.student.usecase.UpdateStudentForgottenPasswordUseCase;
import team.retum.jobis.domain.student.usecase.UpdateStudentPasswordUseCase;
import team.retum.jobis.domain.student.usecase.UpdateStudentProfileUseCase;
import team.retum.jobis.domain.student.usecase.VerifyStudentUseCase;

import static team.retum.jobis.global.config.cache.CacheName.STUDENT_USER;

@RequiredArgsConstructor
@RequestMapping("/students")
@RestController
public class StudentWebAdapter {

    private final StudentSignUpUseCase studentSignUpUseCase;
    private final UpdateStudentForgottenPasswordUseCase updateStudentForgottenPasswordUseCase;
    private final StudentMyPageUseCase studentMyPageUseCase;
    private final VerifyStudentUseCase verifyStudentUseCase;
    private final UpdateStudentProfileUseCase updateStudentProfileUseCase;
    private final CheckStudentPasswordUseCase checkStudentPasswordUseCase;
    private final UpdateStudentPasswordUseCase updateStudentPasswordUseCase;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public TokenResponse signup(@RequestBody @Valid StudentSignUpWebRequest webRequest) {
        return studentSignUpUseCase.execute(webRequest.toDomainRequest());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/forgotten_password")
    public void updateForgottenPassword(@RequestBody @Valid UpdateForgottenPasswordWebRequest webRequest) {
        updateStudentForgottenPasswordUseCase.execute(
            webRequest.getEmail(),
            webRequest.getPassword()
        );
    }

    @GetMapping("/my")
    public StudentMyPageResponse myPage() {
        return studentMyPageUseCase.execute();
    }

    @GetMapping("/exists")
    public void checkStudentExists(
        @RequestParam("gcn") String gcn,
        @RequestParam("name") String name
    ) {
        verifyStudentUseCase.execute(gcn, name);
    }

    @CacheEvict(cacheNames = STUDENT_USER, allEntries = true)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/profile")
    public void updateStudentProfile(@RequestBody @Valid UpdateStudentProfileWebRequest request) {
        updateStudentProfileUseCase.execute(request.getProfileImageUrl());
    }

    @GetMapping("/password")
    public void checkStudentPassword(@RequestParam(value = "password") String password) {
        checkStudentPasswordUseCase.execute(password);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/password")
    public void updatePassword(@RequestBody @Valid UpdatePasswordWebRequest webRequest) {
        updateStudentPasswordUseCase.execute(
            webRequest.getCurrentPassword(),
            webRequest.getNewPassword()
        );
    }
}
