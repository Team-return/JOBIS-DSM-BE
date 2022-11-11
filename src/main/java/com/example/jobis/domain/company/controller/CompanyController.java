package com.example.jobis.domain.company.controller;

import com.example.jobis.domain.company.controller.dto.request.RegisterCompanyRequest;
import com.example.jobis.domain.company.service.RegisterCompanyService;
import com.example.jobis.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/companies")
@RestController
public class CompanyController {

    private final RegisterCompanyService registerCompanyService;
    private final JwtTokenProvider jwtTokenProvider;

//    @ResponseStatus(HttpStatus.CREATED)
//    @PostMapping
//    public void register(@RequestBody @Valid RegisterCompanyRequest request) {
//        registerCompanyService.execute(request);
//    }

    @GetMapping
    public String a() {
        return jwtTokenProvider.generateAccessToken("id");
    }

    @GetMapping("/a")
    public String b() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
