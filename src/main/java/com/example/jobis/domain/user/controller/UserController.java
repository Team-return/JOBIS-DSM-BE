package com.example.jobis.domain.user.controller;

import com.example.jobis.domain.user.controller.dto.request.LoginRequest;
import com.example.jobis.domain.user.controller.dto.response.TokenResponse;
import com.example.jobis.domain.user.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

    private final LoginService loginService;

    @PostMapping
    public TokenResponse login(@RequestBody @Valid LoginRequest request) {
        return loginService.execute(request);
    }
}
