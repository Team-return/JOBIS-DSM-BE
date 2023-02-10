package com.example.jobis.domain.teacher.service;

import com.example.jobis.domain.recruit.domain.Recruitment;
import com.example.jobis.domain.recruit.domain.enums.RecruitStatus;
import com.example.jobis.domain.recruit.domain.repository.RecruitmentJpaRepository;
import com.example.jobis.domain.recruit.facade.RecruitFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ChangeRecruitmentStatusService {
    private final RecruitFacade recruitFacade;

    @Transactional
    public void execute(UUID id, RecruitStatus status) {
        Recruitment recruitment = recruitFacade.getRecruitById(id);
        recruitment.changeStatus(status);
    }
}
