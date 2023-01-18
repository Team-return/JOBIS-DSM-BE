package com.example.jobis.domain.recruit.controller;

import com.example.jobis.domain.recruit.controller.dto.request.ApplyRecruitmentRequest;
import com.example.jobis.domain.recruit.controller.dto.request.UpdateRecruitAreaRequest;
import com.example.jobis.domain.recruit.controller.dto.request.UpdateRecruitmentRequest;
import com.example.jobis.domain.recruit.controller.dto.response.RecruitListResponse;
import com.example.jobis.domain.recruit.service.ApplyRecruitmentService;
import com.example.jobis.domain.recruit.service.QueryRecruitListService;
import com.example.jobis.domain.recruit.service.UpdateRecruitAreaService;
import com.example.jobis.domain.recruit.service.UpdateRecruitmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recruit")
public class RecruitController {

    private final ApplyRecruitmentService applyRecruitmentService;
    private final UpdateRecruitmentService updateRecruitmentService;
    private final UpdateRecruitAreaService updateRecruitAreaService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void applyRecruitment(@RequestBody @Valid ApplyRecruitmentRequest request) {
        applyRecruitmentService.execute(request);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{recruit-id}")
    public void updateRecruitment(@RequestBody @Valid UpdateRecruitmentRequest request, @PathVariable("recruit-id") Long recruitId) {
        updateRecruitmentService.execute(request, recruitId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/area/{recruit-area-id}")
    public void updateRecruitArea(@RequestBody @Valid UpdateRecruitAreaRequest request, @PathVariable("recruit-area-id") Long recruitAreaId) {
        updateRecruitAreaService.execute(request, recruitAreaId);
    }
}
