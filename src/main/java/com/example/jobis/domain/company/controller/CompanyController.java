package com.example.jobis.domain.company.controller;

import com.example.jobis.domain.company.controller.dto.request.UpdateCompanyDetailsRequest;
import com.example.jobis.domain.company.controller.dto.response.CompanyDetailsResponse;
import com.example.jobis.domain.company.controller.dto.response.CompanyListResponse;
import com.example.jobis.domain.company.controller.dto.response.ExistsCompanyResponse;
import com.example.jobis.domain.company.service.*;
import com.example.jobis.domain.user.controller.dto.response.TokenResponse;
import com.example.jobis.domain.company.controller.dto.request.RegisterCompanyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/companies")
@RestController
public class CompanyController {

    private final RegisterCompanyService registerCompanyService;
    private final ExistsCompanyService existsCompanyService;
    private final UpdateCompanyDetailsService updateCompanyDetailsService;
    private final QueryCompanyListService queryCompanyListService;
    private final QueryCompanyDetailsService queryCompanyDetailsService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public TokenResponse register(@RequestBody @Valid RegisterCompanyRequest request) {
        return registerCompanyService.execute(request);
    }

    @GetMapping("/exists/{business-number}")
    public ExistsCompanyResponse companyExists(@PathVariable("business-number") String businessNumber) {
        return existsCompanyService.execute(businessNumber);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping
    public void updateDetails(@RequestBody @Valid UpdateCompanyDetailsRequest request) {
        updateCompanyDetailsService.execute(request);
    }

    @GetMapping
    public CompanyListResponse list() {
        return queryCompanyListService.execute();
    }

    @GetMapping("/{company-id}")
    public CompanyDetailsResponse getCompanyDetails(@PathVariable("company-id") Long companyId) {
        return queryCompanyDetailsService.execute(companyId);
    }
}
