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

    @Column(columnDefinition = "year", nullable = false)
    private int recruitYear;

    @Enumerated(EnumType.STRING)
    private RecruitStatus status;

    @Column(length = 1000)
    private String benefit;

    private int workHours;

    @Column(length = 1000)
    private String preferentialTreatment;

    @Column(columnDefinition = "BIT(1)", nullable = false)
    private boolean military;

    @Column(nullable = false)
    private String hiringProgress;

    @Embedded
    private RecruitDate recruitDate;

    @Embedded
    private Pay pay;

    @Column(nullable = true)
    private String requiredLicenses;

    @Column(columnDefinition = "TINYINT(100)")
    private Integer requiredGrade;

    @Column(length = 1000)
    private String etc;

    @Column(nullable = false)
    private String submitDocument;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @OneToMany(mappedBy = "recruit")
    private List<RecruitArea> recruitAreaList = new ArrayList<>();

    @Builder
    public Recruit(int recruitYear, RecruitStatus status, Integer trainPay, Integer pay, int workHours, String submitDocument,
                   LocalDate startDate, LocalDate endDate, Company company, String benefit, String requiredLicenses,
                   boolean military, String etc, String preferentialTreatment, String hiringProgress, Integer requiredGrade
    ) {
        this.workHours = workHours;
        this.hiringProgress = hiringProgress;
        this.submitDocument = submitDocument;
        this.requiredGrade = requiredGrade;
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

    public void update(Integer trainPay, Integer pay, int workHours, String submitDocument,
                       LocalDate startDate, LocalDate endDate, String benefit, String requiredLicenses,
                       boolean military, String etc, String preferentialTreatment, String hiringProgress, Integer requiredGrade
    ) {
        this.workHours = workHours;
        this.hiringProgress = hiringProgress;
        this.submitDocument = submitDocument;
        this.requiredGrade = requiredGrade;
        this.benefit = benefit;
        this.preferentialTreatment = preferentialTreatment;
        this.recruitDate = new RecruitDate(startDate, endDate);
        this.pay = new Pay(trainPay, pay);
        this.requiredLicenses = requiredLicenses;
        this.military = military;
        this.etc = etc;
    }
}
