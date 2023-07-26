package com.example.jobisapplication.domain.recruitment.spi.vo;

import com.example.jobisapplication.domain.company.model.CompanyType;
import com.example.jobisapplication.domain.recruitment.model.RecruitStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class RecruitmentVO {

    private final Long recruitmentId;
    private final RecruitStatus recruitStatus;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final String companyName;
    private final CompanyType companyType;
    private final Integer trainPay;
    private final boolean militarySupport;
    private final String companyLogoUrl;
    private final String recruitAreaList;
    private final Integer totalHiring;
    private final Long requestedApplicationCount;
    private final Long approvedApplicationCount;
    private final Long isBookmarked;

}
