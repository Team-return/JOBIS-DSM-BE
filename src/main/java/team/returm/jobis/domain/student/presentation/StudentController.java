package team.returm.jobis.domain.student.presentation;

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
import team.returm.jobis.domain.student.presentation.dto.request.StudentSignUpRequest;
import team.returm.jobis.domain.student.presentation.dto.request.UpdatePasswordRequest;
import team.returm.jobis.domain.student.presentation.dto.response.StudentMyPageResponse;
import team.returm.jobis.domain.student.service.StudentMyPageService;
import team.returm.jobis.domain.student.service.StudentSignUpService;
import team.returm.jobis.domain.student.service.UpdateStudentPasswordService;
import team.returm.jobis.domain.student.service.VerifyStudentService;
import team.returm.jobis.domain.user.presentation.dto.response.TokenResponse;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/students")
@RestController
public class StudentController {

    private final StudentSignUpService studentSignUpService;
    private final UpdateStudentPasswordService updateStudentPasswordService;
    private final StudentMyPageService studentMyPageService;
    private final VerifyStudentService verifyStudentService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public TokenResponse signup(@RequestBody @Valid StudentSignUpRequest request) {
        return studentSignUpService.execute(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/password")
    public void updatePassword(@RequestBody @Valid UpdatePasswordRequest request) {
        updateStudentPasswordService.execute(request);
    }

    @GetMapping("/my")
    public StudentMyPageResponse mainPage() {
        return studentMyPageService.execute();
    }
      
    @GetMapping("/exists")
    public void checkStudentExists(
            @RequestParam("gcn") String gcn,
            @RequestParam("name") String name
    ) {
        verifyStudentService.execute(gcn, name);
    }
}
