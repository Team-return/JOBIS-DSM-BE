package com.example.jobis.domain.recruit.domain;

import com.example.jobis.domain.company.domain.Company;
import com.example.jobis.domain.recruit.domain.enums.RecruitStatus;
import com.example.jobis.domain.recruit.domain.type.Pay;
import com.example.jobis.domain.recruit.domain.type.RecruitDate;
import com.example.jobis.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Year;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Recruit extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recruit_id")
    private Long id;

    private Year recruitYear;

    @Enumerated(EnumType.STRING)
    private RecruitStatus status;

    private String benefit;

    private boolean to;

    @Embedded
    private RecruitDate recruitDate;

    @Embedded
    private Pay pay;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @Builder
    public Recruit(Year year, RecruitStatus status, Integer trainPay, Integer pay,
                   LocalDate startDate, LocalDate endDate, Company company, String benefit, boolean to) {
        this.recruitYear = year;
        this.status = status;
        this.benefit = benefit;
        this.recruitDate = new RecruitDate(startDate, endDate);
        this.pay = new Pay(trainPay, pay);
        this.company = company;
        this.to = to;
    }
}
