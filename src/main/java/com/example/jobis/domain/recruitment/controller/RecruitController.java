package com.example.jobis.domain.recruitment.controller;

import com.example.jobis.domain.code.controller.dto.request.CreateRecruitAreaCodeRequest;
import com.example.jobis.domain.code.controller.dto.request.CreateRecruitAreaRequest;
import com.example.jobis.domain.recruitment.service.CreateCompanyRecruitAreaCodeService;
import com.example.jobis.domain.recruitment.service.DeleteCompanyRecruitAreaCodeService;
import com.example.jobis.domain.recruitment.controller.dto.request.ApplyRecruitmentRequest;
import com.example.jobis.domain.recruitment.controller.dto.request.UpdateRecruitAreaRequest;
import com.example.jobis.domain.recruitment.controller.dto.request.UpdateRecruitmentRequest;
import com.example.jobis.domain.recruitment.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recruitment")
public class RecruitController {

    private final ApplyRecruitmentService applyRecruitmentService;
    private final UpdateCompanyRecruitService updateCompanyRecruitService;
    private final UpdateCompanyRecruitAreaService updateCompanyRecruitAreaService;
    private final DeleteCompanyRecruitAreaCodeService deleteCompanyRecruitAreaCodeService;
    private final CreateCompanyRecruitAreaCodeService createCompanyRecruitAreaCodeService;
    private final CreateCompanyRecruitAreaService createCompanyRecruitAreaService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void applyRecruitment(@RequestBody @Valid ApplyRecruitmentRequest request) {
        applyRecruitmentService.execute(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping
    public void updateRecruitment(@RequestBody @Valid UpdateRecruitmentRequest request) {
        updateCompanyRecruitService.execute(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/area/{recruit-area-id}")
    public void updateRecruitArea(@RequestBody @Valid UpdateRecruitAreaRequest request, @PathVariable("recruit-area-id") UUID recruitAreaId) {
        updateCompanyRecruitAreaService.execute(request, recruitAreaId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/area/code")
    public void deleteRecruitAreaCode(@RequestParam("recruit-area-id") UUID recruitAreaId, @RequestParam("code-id") Long codeId) {
        deleteCompanyRecruitAreaCodeService.execute(recruitAreaId, codeId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/area/code/{recruit-area-id}")
    public void createRecruitAreaCode(@RequestBody @Valid CreateRecruitAreaCodeRequest request, @PathVariable("recruit-area-id") UUID recruitAreaId) {
        createCompanyRecruitAreaCodeService.execute(recruitAreaId, request);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/area")
    public void createRecruitArea(@RequestBody @Valid CreateRecruitAreaRequest request) {
        createCompanyRecruitAreaService.execute(request);
    }
}
