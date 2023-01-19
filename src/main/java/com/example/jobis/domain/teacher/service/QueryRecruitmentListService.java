package com.example.jobis.domain.teacher.service;

import com.example.jobis.domain.recruit.domain.repository.RecruitmentRepository;
import com.example.jobis.domain.teacher.presentaion.dto.QueryRecruitmentListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class QueryRecruitmentListService {
    private final RecruitmentRepository recruitmentRepository;

    @Transactional(readOnly = true)
    public QueryRecruitmentListResponse execute() {

    }
}
