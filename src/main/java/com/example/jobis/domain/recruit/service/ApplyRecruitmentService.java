package com.example.jobis.domain.recruit.service;

import com.example.jobis.domain.recruit.domain.repository.RecruitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplyRecruitmentService {
    private final RecruitRepository recruitRepository;

    public void execute() {

    }
}
