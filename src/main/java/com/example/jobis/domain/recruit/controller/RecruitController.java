package com.example.jobis.domain.recruit.controller;

import com.example.jobis.domain.recruit.controller.dto.request.ApplyRecruitmentRequest;
import com.example.jobis.domain.recruit.controller.dto.response.RecruitDetailsResponse;
import com.example.jobis.domain.recruit.service.ApplyRecruitmentService;
import com.example.jobis.domain.recruit.service.QueryRecruitDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recruit")
public class RecruitController {
    private final ApplyRecruitmentService applyRecruitmentService;
    private final QueryRecruitDetailsService queryRecruitDetailsService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void applyRecruitment(@RequestBody @Valid ApplyRecruitmentRequest request) {
        applyRecruitmentService.execute(request);
    }

    @GetMapping
    public RecruitDetailsResponse getDetails(@RequestParam("id") Long recruitId, @RequestParam("year") int year) {
        return queryRecruitDetailsService.execute(recruitId, year);
    }
}
