package com.example.jobis.domain.recruit.controller;

import com.example.jobis.domain.recruit.service.ApplyRecruitmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recruit")
public class RecruitController {
    private final ApplyRecruitmentService applyRecruitmentService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void applyRecruitment() {
        applyRecruitmentService
    }
}
