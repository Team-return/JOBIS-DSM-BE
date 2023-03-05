package com.example.jobis.domain.recruitment.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
public class StudentRecruitDetailsResponse {

    private List<RecruitAreaResponse> areas;
    private String preferentialTreatment;
    private Integer requiredGrade;
    private int workHours;
    private String requiredLicenses;

    private String hiringProgress;
    private Integer trainPay;
    private Integer pay;
    private String benefits;
    private boolean military;
    private String submitDocument;
    private LocalDate startDate;
    private LocalDate endDate;
    private String etc;

    @Getter
    @Builder
    public static class RecruitAreaResponse {
        private UUID recruitAreaId;
        private List<String> job;
        private List<String> tech;
        private int hiring;
        private String majorTask;
    }
}
