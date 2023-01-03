package com.example.jobis.domain.recruit.controller.dto.response;

import com.example.jobis.domain.code.domain.Code;
import com.example.jobis.domain.code.domain.RecruitAreaCode;
import com.example.jobis.domain.code.domain.enums.CodeType;
import com.example.jobis.domain.code.facade.CodeFacade;
import com.example.jobis.domain.recruit.domain.RecruitArea;
import com.example.jobis.domain.recruit.domain.enums.ProgressType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class RecruitDetailsResponse {

    private List<RecruitAreaResponse> areas;
    private String preferentialTreatment;
    private int requiredGrade;
    private int workHours;
    private List<String> requiredLicenses;

    private String hiringProgress;
    private int trainPay;
    private int pay;
    private String benefits;
    private boolean military;
    private String submitDocument;
    private LocalDate startDate;
    private LocalDate endDate;
    private String etc;

    @Getter
    @Builder
    public static class RecruitAreaResponse {
        private List<String> job;
        private List<String> tech;
        private int hiring;
        private String majorTask;

        public static RecruitAreaResponse of(RecruitArea recruitArea) {

            List<String> jobCodes = CodeFacade.getKeywordByRecruitArea(recruitArea, CodeType.JOB);
            List<String> techCodes = CodeFacade.getKeywordByRecruitArea(recruitArea, CodeType.TECH);

            return RecruitAreaResponse.builder()
                    .job(jobCodes)
                    .tech(techCodes)
                    .hiring(recruitArea.getHiredCount())
                    .majorTask(recruitArea.getMajorTask())
                    .build();
        }
    }
}
