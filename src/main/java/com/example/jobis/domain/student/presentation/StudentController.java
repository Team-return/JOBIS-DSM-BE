package com.example.jobis.domain.student.presentation;

import com.example.jobis.domain.recruitment.presentation.dto.response.StudentRecruitDetailsResponse;
import com.example.jobis.domain.recruitment.service.StudentQueryRecruitmentDetailService;
import com.example.jobis.domain.student.presentation.dto.request.SendAuthCodeRequest;
import com.example.jobis.domain.student.presentation.dto.request.StudentSignUpRequest;
import com.example.jobis.domain.student.presentation.dto.request.VerifyAuthCodeRequest;
import com.example.jobis.domain.student.service.SendSignUpAuthCodeService;
import com.example.jobis.domain.student.service.StudentSignUpService;
import com.example.jobis.domain.student.service.VerifyAuthCodeService;
import com.example.jobis.domain.user.presentation.dto.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/students")
@RestController
public class StudentController {

    private final SendSignUpAuthCodeService sendSignUpAuthCodeService;
    private final VerifyAuthCodeService verifyAuthCodeService;
    private final StudentSignUpService studentSignUpService;
    private final StudentQueryRecruitmentDetailService studentQueryRecruitmentDetailService;

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

    @GetMapping("/recruitment/{recruitment-id}")
    public StudentRecruitDetailsResponse queryRecruitmentDetails(@PathVariable("recruitment-id") UUID recruitmentId) {
        return studentQueryRecruitmentDetailService.execute(recruitmentId);
    }
}
