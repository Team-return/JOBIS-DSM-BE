package com.example.jobis.domain.recruitment.service;

import com.example.jobis.domain.recruitment.domain.Recruitment;
import com.example.jobis.domain.recruitment.domain.enums.RecruitStatus;
import com.example.jobis.domain.recruitment.facade.RecruitFacade;
import com.example.jobis.global.annotation.Service;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ChangeRecruitmentStatusService {
    private final RecruitFacade recruitFacade;

    public void execute(UUID id, RecruitStatus status) {
        Recruitment recruitment = recruitFacade.queryRecruitmentById(id);
        recruitment.changeStatus(status);
    }
}
