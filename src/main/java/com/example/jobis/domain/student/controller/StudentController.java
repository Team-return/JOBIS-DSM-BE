package com.example.jobis.domain.student.controller;

import com.example.jobis.domain.recruitment.controller.dto.response.StudentRecruitDetailsResponse;
import com.example.jobis.domain.recruitment.controller.dto.response.StudentRecruitListResponse;
import com.example.jobis.domain.recruitment.service.StudentQueryRecruitmentsService;
import com.example.jobis.domain.recruitment.service.QueryStudentRecruitDetailsService;
import com.example.jobis.domain.student.controller.dto.request.SendAuthCodeRequest;
import com.example.jobis.domain.student.controller.dto.request.StudentSignUpRequest;
import com.example.jobis.domain.student.controller.dto.request.VerifyAuthCodeRequest;
import com.example.jobis.domain.student.service.SendSignUpAuthCodeService;
import com.example.jobis.domain.student.service.StudentSignUpService;
import com.example.jobis.domain.student.service.VerifyAuthCodeService;
import com.example.jobis.domain.user.controller.dto.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/students")
@RestController
public class StudentController {

    private final SendSignUpAuthCodeService sendSignUpAuthCodeService;
    private final VerifyAuthCodeService verifyAuthCodeService;
    private final StudentSignUpService studentSignUpService;
    private final StudentQueryRecruitmentsService studentQueryRecruitmentsService;
    private final QueryStudentRecruitDetailsService queryStudentRecruitDetailsService;

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

    @GetMapping("/recruitment")
    public List<StudentRecruitListResponse> queryRecruitmentList(
            @RequestParam(value = "name", required = false) String companyName,
            @RequestParam(value = "page", defaultValue = "1") Integer page
    ) {
        return studentQueryRecruitmentsService.execute(companyName, page);
    }

    @GetMapping("/recruitment/{recruitment-id}")
    public StudentRecruitDetailsResponse queryRecruitmentDetails(@PathVariable("recruitment-id") UUID recruitmentId) {
        return queryStudentRecruitDetailsService.execute(recruitmentId);
    }
}
