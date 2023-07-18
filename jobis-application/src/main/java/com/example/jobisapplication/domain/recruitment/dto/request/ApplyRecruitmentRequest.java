package com.example.jobisapplication.domain.recruitment.dto.request;

import com.example.jobisapplication.domain.recruitment.model.ProgressType;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class ApplyRecruitmentRequest {

    private List<RecruitAreaRequest> areas;

    private String preferentialTreatment;

    private Integer requiredGrade;

    private int workHours;

    private List<String> requiredLicenses;

    private List<ProgressType> hiringProgress;

    private int trainPay;

    private Integer pay;

    private String benefits;

    private boolean militarySupport;

    private boolean personalContact;

    private String submitDocument;

    private LocalDate startDate;

    private LocalDate endDate;

    private String etc;
}
