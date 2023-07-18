package com.example.jobisapplication.domain.recruitment.model;

import com.example.jobisapplication.common.annotation.Aggregate;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@Aggregate
public class Recruitment {

    private final Long id;

    private final Integer recruitYear;

    private final RecruitStatus status;

    private final String preferentialTreatment;

    private final List<String> requiredLicenses;

    private final Integer requiredGrade;

    private final Integer workingHours;

    private final String benefits;

    private final boolean militarySupport;

    private final List<ProgressType> hiringProgress;

    private final String submitDocument;

    private final String etc;

    private final LocalDate startDate;

    private final LocalDate finishDate;

    private final Integer trainPay;

    private final Integer pay;

    private final boolean personalContract;

    private final Long companyId;

}
