package com.example.jobis.domain.recruit.controller.dto.response;

import com.example.jobis.domain.recruit.domain.RecruitArea;
import com.example.jobis.domain.recruit.domain.enums.ProgressType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

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

}
