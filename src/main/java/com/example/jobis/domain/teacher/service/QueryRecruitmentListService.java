package com.example.jobis.domain.teacher.service;

import com.example.jobis.domain.recruit.domain.repository.RecruitmentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class QueryRecruitmentListService {
    private final RecruitmentJpaRepository recruitmentJpaRepository;

    @Transactional(readOnly = true)
    public void execute() {

    }
}
