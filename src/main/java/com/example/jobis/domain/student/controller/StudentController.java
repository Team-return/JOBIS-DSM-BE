package com.example.jobis.domain.student.controller;

import com.example.jobis.domain.recruit.controller.dto.response.RecruitListResponse;
import com.example.jobis.domain.recruit.service.QueryRecruitListService;
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

@RequiredArgsConstructor
@RequestMapping("/students")
@RestController
public class StudentController {

    private final SendSignUpAuthCodeService sendSignUpAuthCodeService;
    private final VerifyAuthCodeService verifyAuthCodeService;
    private final StudentSignUpService studentSignUpService;
    private final QueryRecruitListService queryRecruitListService;

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
    @PostMapping
    public TokenResponse signup(@RequestBody @Valid StudentSignUpRequest request) {
        return studentSignUpService.execute(request);
    }

    @GetMapping("/recruit")
    public RecruitListResponse getRecruitList() {
        return queryRecruitListService.execute();
    }
}
