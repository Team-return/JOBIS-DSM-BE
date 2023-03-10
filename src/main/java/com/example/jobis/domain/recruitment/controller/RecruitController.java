package com.example.jobis.domain.recruitment.controller;

import com.example.jobis.domain.code.controller.dto.request.CreateRecruitAreaRequest;
import com.example.jobis.domain.recruitment.controller.dto.request.ApplyRecruitmentRequest;
import com.example.jobis.domain.recruitment.controller.dto.request.UpdateRecruitAreaRequest;
import com.example.jobis.domain.recruitment.controller.dto.request.UpdateRecruitmentRequest;
import com.example.jobis.domain.recruitment.service.ApplyRecruitmentService;
import com.example.jobis.domain.recruitment.service.CreateCompanyRecruitAreaService;
import com.example.jobis.domain.recruitment.service.UpdateRecruitAreaService;
import com.example.jobis.domain.recruitment.service.UpdateRecruitmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recruitment")
public class RecruitController {

    private final ApplyRecruitmentService applyRecruitmentService;
    private final UpdateRecruitmentService updateRecruitmentService;
    private final UpdateRecruitAreaService updateRecruitAreaService;
    private final CreateCompanyRecruitAreaService createCompanyRecruitAreaService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void applyRecruitment(@RequestBody @Valid ApplyRecruitmentRequest request) {
        applyRecruitmentService.execute(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{recruitment-id}")
    public void updateRecruitment(
            @RequestBody @Valid UpdateRecruitmentRequest request,
            @PathVariable("recruitment-id") UUID recruitmentId
    ) {
        updateRecruitmentService.execute(request, recruitmentId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/area/{recruit-area-id}")
    public void updateRecruitArea(
            @RequestBody @Valid UpdateRecruitAreaRequest request,
            @PathVariable("recruit-area-id") UUID recruitAreaId
    ) {
        updateRecruitAreaService.execute(request, recruitAreaId);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/area")
    public void createRecruitArea(@RequestBody @Valid CreateRecruitAreaRequest request) {
        createCompanyRecruitAreaService.execute(request);
    }
}
