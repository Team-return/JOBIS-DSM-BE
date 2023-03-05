package com.example.jobis.domain.recruitment.service;

import com.example.jobis.domain.recruitment.controller.dto.request.UpdateRecruitAreaRequest;
import com.example.jobis.domain.recruitment.domain.RecruitArea;
import com.example.jobis.domain.recruitment.facade.RecruitAreaFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UpdateRecruitAreaService {

    private final RecruitAreaFacade recruitAreaFacade;

    @Transactional
    public void execute(UpdateRecruitAreaRequest request, UUID recruitAreaId) {

        RecruitArea recruitArea = recruitAreaFacade.getRecruitAreaById(recruitAreaId);
        recruitArea.update(request.getHiring(), request.getMajorTask());
    }
}
