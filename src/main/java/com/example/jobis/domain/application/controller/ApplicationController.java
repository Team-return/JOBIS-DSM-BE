package com.example.jobis.domain.application.controller;

import com.example.jobis.domain.application.controller.dto.request.CreateApplicationRequest;
import com.example.jobis.domain.application.service.CreateApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/applications")
@RestController
public class ApplicationController {

    private final CreateApplicationService createApplicationService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{company-id}")
    public void execute(@RequestBody @Valid CreateApplicationRequest request, @PathVariable("company-id") Long companyId) {
        createApplicationService.execute(request, companyId);
    }
}
