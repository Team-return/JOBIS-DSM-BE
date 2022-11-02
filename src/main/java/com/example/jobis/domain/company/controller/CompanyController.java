package com.example.jobis.domain.company.controller;

import com.example.jobis.domain.company.controller.dto.request.RegisterCompanyRequest;
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

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void register(@RequestBody @Valid RegisterCompanyRequest request) {
        registerCompanyService.execute(request);
    }
}
