package com.example.jobis.domain.company.controller;

import com.example.jobis.domain.auth.controller.dto.response.TokenResponse;
import com.example.jobis.domain.company.controller.dto.request.CompanyDetailsRequest;
import com.example.jobis.domain.company.controller.dto.request.RegisterCompanyRequest;
import com.example.jobis.domain.company.service.CreateCompanyDetailsService;
import com.example.jobis.domain.company.service.RegisterCompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/companies")
@RestController
public class CompanyController {

    private final RegisterCompanyService registerCompanyService;
    private final CreateCompanyDetailsService createCompanyDetailsService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public TokenResponse register(@RequestBody @Valid RegisterCompanyRequest request) {
        return registerCompanyService.execute(request);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/details")
    public void companyDetails(@RequestBody @Valid CompanyDetailsRequest request) {
        createCompanyDetailsService.execute(request);
    }
}
