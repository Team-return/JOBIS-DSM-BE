package com.example.jobis.domain.recruitment.service;

import com.example.jobis.domain.recruitment.controller.dto.request.UpdateRecruitmentRequest;
import com.example.jobis.domain.recruitment.domain.Recruitment;
import com.example.jobis.domain.recruitment.facade.RecruitFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class UpdateRecruitmentService {

    private final RecruitFacade recruitFacade;

    @Transactional
    public void execute(UpdateRecruitmentRequest request, UUID recruitId) {

        Recruitment recruit = recruitFacade.getRecruitById(recruitId);

        String hiringProgress = request.getHiringProgress()
                .stream().map(Enum::toString)
                .collect(Collectors.joining(","));
        String requiredLicenses = request.getRequiredLicenses() == null?
                null : String.join(",", request.getRequiredLicenses());

        recruit.update(
                request.getTrainPay(), request.getPay(), request.getWorkHours(), request.getSubmitDocument(),
                request.getStartDate(), request.getEndDate(), request.getBenefits(), requiredLicenses,
                request.isMilitary(), request.getEtc(), request.getPreferentialTreatment(), hiringProgress,
                request.getRequiredGrade()
        );
    }
}
