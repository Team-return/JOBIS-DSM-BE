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
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Recruit extends BaseTimeEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "recruit_id")
    private Long id;

    @Column(columnDefinition = "year")
    private int recruitYear;

    @Enumerated(EnumType.STRING)
    private RecruitStatus status;

    @Column(nullable = false)
    private int requiredGrade;

    @Column(nullable = false)
    private int workHours;

    @Column(length = 500, nullable = false)
    private String submitDocument;

    @Column(length = 1000)
    private String benefit;

    @Column(length = 1000)
    private String preferentialTreatment;

    @Column(columnDefinition = "BIT(1)", nullable = false)
    private boolean military;

    @Column(length = 1000)
    private String etc;

    @Column(nullable = false)
    private String hiringProgress;

    @Embedded
    private RecruitDate recruitDate;

    @Embedded
    private Pay pay;

    @Column(nullable = false)
    private String requiredLicenses;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @OneToMany(mappedBy = "recruit")
    private List<RecruitArea> recruitAreaList = new ArrayList<>();

    @Builder
    public Recruit(int recruitYear, RecruitStatus status, int requiredGrade, int workHours, String submitDocument, Integer trainPay, Integer pay,
                   LocalDate startDate, LocalDate endDate, Company company, String benefit, String requiredLicenses,
                   boolean military, String etc, String preferentialTreatment, String hiringProgress
    ) {
        this.requiredGrade = requiredGrade;
        this.workHours = workHours;
        this.submitDocument = submitDocument;
        this.hiringProgress = hiringProgress;
        this.recruitYear = recruitYear;
        this.status = status;
        this.benefit = benefit;
        this.preferentialTreatment = preferentialTreatment;
        this.recruitDate = new RecruitDate(startDate, endDate);
        this.pay = new Pay(trainPay, pay);
        this.company = company;
        this.requiredLicenses = requiredLicenses;
        this.military = military;
        this.etc = etc;
    }
}
