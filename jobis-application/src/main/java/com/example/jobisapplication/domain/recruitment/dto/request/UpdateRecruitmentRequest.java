package com.example.jobisapplication.domain.recruitment.dto.request;

import com.example.jobisapplication.domain.recruitment.model.ProgressType;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class UpdateRecruitmentRequest {

    private String preferentialTreatment;

    private Integer requiredGrade;

    private Integer workHours;

    private List<String> requiredLicenses;

    private List<ProgressType> hiringProgress;

    private Integer trainPay;

    private Integer pay;

    private String benefits;

    private boolean military;

    private String submitDocument;

    private LocalDate startDate;

    private LocalDate endDate;

    private String etc;
}
