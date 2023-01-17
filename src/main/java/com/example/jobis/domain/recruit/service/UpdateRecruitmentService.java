package com.example.jobis.domain.recruit.service;

import com.example.jobis.domain.recruit.controller.dto.request.UpdateRecruitmentRequest;
import com.example.jobis.domain.recruit.domain.Recruit;
import com.example.jobis.domain.recruit.facade.RecruitFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class UpdateRecruitmentService {

    private final RecruitFacade recruitFacade;

    @Transactional
    public void execute(UpdateRecruitmentRequest request, Long recruitId) {

        Recruit recruit = recruitFacade.getRecruitById(recruitId);

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
