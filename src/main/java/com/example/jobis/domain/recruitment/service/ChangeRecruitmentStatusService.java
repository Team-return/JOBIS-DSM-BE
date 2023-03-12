package com.example.jobis.domain.recruitment.service;

import com.example.jobis.domain.recruitment.domain.Recruitment;
import com.example.jobis.domain.recruitment.domain.enums.RecruitStatus;
import com.example.jobis.domain.recruitment.facade.RecruitFacade;
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
        Recruitment recruitment = recruitFacade.queryRecruitmentById(id);
        recruitment.changeStatus(status);
    }
}
