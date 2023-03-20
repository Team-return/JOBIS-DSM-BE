package team.returm.jobis.domain.recruitment.domain;

import java.time.LocalDateTime;
import team.returm.jobis.domain.application.domain.Application;
import team.returm.jobis.domain.company.domain.Company;
import team.returm.jobis.domain.recruitment.domain.enums.RecruitStatus;
import team.returm.jobis.domain.recruitment.domain.type.PayInfo;
import team.returm.jobis.domain.recruitment.domain.type.RecruitDate;
import team.returm.jobis.domain.recruitment.exception.CompanyMismatchException;
import team.returm.jobis.global.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Recruitment extends BaseEntity {

    @NotNull
    @Column(columnDefinition = "YEAR")
    private Integer recruitYear;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10)")
    private RecruitStatus status;

    @Column(columnDefinition = "VARCHAR(255)")
    private String preferentialTreatment;

    @Column(columnDefinition = "VARCHAR(200)")
    private String requiredLicenses;

    @Column(columnDefinition = "TINYINT(100)")
    private Integer requiredGrade;

    @NotNull
    @Column(columnDefinition = "TINYINT(50)", nullable = false)
    private Integer workingHours;

    @Column(columnDefinition = "VARCHAR(300)")
    private String benefits;

    @NotNull
    @Column(columnDefinition = "BOOL")
    private Boolean militarySupport;

    @NotNull
    @Column(columnDefinition = "VARCHAR(100)")
    private String hiringProgress;

    @Column(columnDefinition = "VARCHAR(100)")
    private String submitDocument;

    @Column(columnDefinition = "VARCHAR(350)")
    private String etc;

    @Embedded
    private RecruitDate recruitDate;

    @Embedded
    private PayInfo payInfo;

    @Column(columnDefinition = "INT")
    private Integer applicationCount;

    @Column(columnDefinition = "DATETIME(8)")
    private LocalDateTime deletedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @OneToMany(mappedBy = "recruitment")
    private List<RecruitArea> recruitAreaList = new ArrayList<>();

    @OneToMany(mappedBy = "recruitment")
    private List<Application> applications = new ArrayList<>();

    @Builder
    public Recruitment(int recruitYear, RecruitStatus status, Integer trainPay, Integer pay, int workingHours, String submitDocument,
                       LocalDate startDate, LocalDate endDate, Company company, String benefits, String requiredLicenses,
                       boolean militarySupport, String etc, String preferentialTreatment, String hiringProgress, Integer requiredGrade
    ) {
        this.workingHours = workingHours;
        this.hiringProgress = hiringProgress;
        this.submitDocument = submitDocument;
        this.requiredGrade = requiredGrade;
        this.recruitYear = recruitYear;
        this.status = status;
        this.benefits = benefits;
        this.preferentialTreatment = preferentialTreatment;
        this.applicationCount = 0;
        this.recruitDate = new RecruitDate(startDate, endDate);
        this.payInfo = new PayInfo(trainPay, pay);
        this.company = company;
        this.requiredLicenses = requiredLicenses;
        this.militarySupport = militarySupport;
        this.etc = etc;
    }

    public void update(Integer trainPay, Integer pay, int workingHours, String submitDocument,
                       LocalDate startDate, LocalDate endDate, String benefits, String requiredLicenses,
                       boolean militarySupport, String etc, String preferentialTreatment, String hiringProgress, Integer requiredGrade
    ) {
        this.workingHours = workingHours;
        this.hiringProgress = hiringProgress;
        this.submitDocument = submitDocument;
        this.requiredGrade = requiredGrade;
        this.benefits = benefits;
        this.preferentialTreatment = preferentialTreatment;
        this.recruitDate = new RecruitDate(startDate, endDate);
        this.payInfo = new PayInfo(trainPay, pay);
        this.requiredLicenses = requiredLicenses;
        this.militarySupport = militarySupport;
        this.etc = etc;
    }

    public Recruitment changeStatus(RecruitStatus status) {
        this.status = status;
        return this;
    }

    public void checkCompany(UUID companyId) {
        if (!this.getCompany().getId().equals(companyId)) {
            throw CompanyMismatchException.EXCEPTION;
        }
    }

    public void addApplicationCount() {
        this.applicationCount += 1;
    }

    public void subApplicationCount() {
        this.applicationCount -= 1;
    }

    public Recruitment deleteRecruitment() {
        this.deletedAt = LocalDateTime.now();
    }
}
