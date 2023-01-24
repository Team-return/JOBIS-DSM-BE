package com.example.jobis.domain.recruit.controller;

import com.example.jobis.domain.code.controller.dto.request.CreateRecruitAreaCodeRequest;
import com.example.jobis.domain.recruit.service.CreateCompanyRecruitAreaService;
import com.example.jobis.domain.recruit.service.DeleteCompanyRecruitAreaCodeService;
import com.example.jobis.domain.recruit.controller.dto.request.ApplyRecruitmentRequest;
import com.example.jobis.domain.recruit.controller.dto.request.UpdateRecruitAreaRequest;
import com.example.jobis.domain.recruit.controller.dto.request.UpdateRecruitmentRequest;
import com.example.jobis.domain.recruit.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recruitment")
public class RecruitController {

    private final ApplyRecruitmentService applyRecruitmentService;
    private final UpdateCompanyRecruitService updateCompanyRecruitService;
    private final UpdateCompanyRecruitAreaService updateCompanyRecruitAreaService;
    private final DeleteCompanyRecruitAreaCodeService deleteCompanyRecruitAreaCodeService;
    private final CreateCompanyRecruitAreaService createCompanyRecruitAreaService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void applyRecruitment(@RequestBody @Valid ApplyRecruitmentRequest request) {
        applyRecruitmentService.execute(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{recruit-id}")
    public void updateRecruitment(@RequestBody @Valid UpdateRecruitmentRequest request) {
        updateCompanyRecruitService.execute(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/area/{recruit-area-id}")
    public void updateRecruitArea(@RequestBody @Valid UpdateRecruitAreaRequest request, @PathVariable("recruit-area-id") Long recruitAreaId) {
        updateCompanyRecruitAreaService.execute(request, recruitAreaId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/area/code")
    public void deleteRecruitAreaCode(@RequestParam("recruit-area-id") Long recruitAreaId, @RequestParam("code-id") Long codeId) {
        deleteCompanyRecruitAreaCodeService.execute(recruitAreaId, codeId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/area/code/{recruit-area-id}")
    public void createRecruitAreaCode(@RequestBody @Valid CreateRecruitAreaCodeRequest request, @PathVariable("recruit-area-id") Long recruitAreaId) {
        createCompanyRecruitAreaService.execute(recruitAreaId, request);
    }
}
