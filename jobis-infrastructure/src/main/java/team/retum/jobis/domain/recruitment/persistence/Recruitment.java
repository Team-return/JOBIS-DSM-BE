package team.retum.jobis.domain.recruitment.persistence;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import team.retum.jobis.domain.application.persistence.ApplicationEntity;
import team.retum.jobis.domain.company.persistence.Company;
import team.retum.jobis.domain.recruitment.persistence.enums.ProgressType;
import team.retum.jobis.domain.recruitment.persistence.enums.RecruitStatus;
import team.retum.jobis.domain.recruitment.persistence.repository.converter.HiringProgressConverter;
import team.retum.jobis.domain.recruitment.persistence.type.PayInfo;
import team.retum.jobis.domain.recruitment.persistence.type.RecruitDate;
import team.retum.jobis.domain.recruitment.exception.CompanyMismatchException;
import team.retum.jobis.domain.recruitment.exception.InvalidRecruitmentStatusException;
import team.retum.jobis.global.converter.StringListConverter;
import team.retum.jobis.global.entity.BaseTimeEntity;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Recruitment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(columnDefinition = "YEAR")
    private Integer recruitYear;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10)")
    private RecruitStatus status;

    @Column(columnDefinition = "VARCHAR(500)")
    private String preferentialTreatment;

    @Convert(converter = StringListConverter.class)
    @Column(columnDefinition = "VARCHAR(200)")
    private List<String> requiredLicenses;

    @Column(columnDefinition = "TINYINT(100)")
    private Integer requiredGrade;

    @NotNull
    @Column(columnDefinition = "TINYINT(50)", nullable = false)
    private Integer workingHours;

    @Column(columnDefinition = "VARCHAR(300)")
    private String benefits;

    @NotNull
    @Column(columnDefinition = "BIT(1)")
    private Boolean militarySupport;

    @NotNull
    @Convert(converter = HiringProgressConverter.class)
    @Column(columnDefinition = "VARCHAR(100)")
    private List<ProgressType> hiringProgress;

    @NotNull
    @Column(columnDefinition = "VARCHAR(100)")
    private String submitDocument;

    @Column(columnDefinition = "VARCHAR(350)")
    private String etc;

    @Embedded
    private RecruitDate recruitDate;

    @Embedded
    private PayInfo payInfo;

    @NotNull
    @Column(columnDefinition = "BIT(1)")
    private Boolean personalContact;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @OneToMany(mappedBy = "recruitment", orphanRemoval = true)
    private final List<RecruitArea> recruitAreas = new ArrayList<>();

    @OneToMany(mappedBy = "recruitment")
    private final List<ApplicationEntity> applicationEntities = new ArrayList<>();

    @Builder
    public Recruitment(int recruitYear, RecruitStatus status, Integer trainPay, Integer pay, int workingHours, String submitDocument,
                       LocalDate startDate, LocalDate endDate, Company company, String benefits, boolean militarySupport, List<String> requiredLicenses,
                       String etc, String preferentialTreatment, List<ProgressType> hiringProgress, Integer requiredGrade, Boolean personalContact) {
        this.workingHours = workingHours;
        this.hiringProgress = hiringProgress;
        this.submitDocument = submitDocument;
        this.requiredGrade = requiredGrade;
        this.recruitYear = recruitYear;
        this.status = status;
        this.benefits = benefits;
        this.preferentialTreatment = preferentialTreatment;
        this.personalContact = personalContact;
        this.recruitDate = new RecruitDate(startDate, endDate);
        this.payInfo = new PayInfo(trainPay, pay);
        this.company = company;
        this.requiredLicenses = requiredLicenses;
        this.militarySupport = militarySupport;
        this.etc = etc;
    }

    public void update(Integer trainPay, Integer pay, int workingHours, String submitDocument,
                       LocalDate startDate, LocalDate endDate, String benefits, List<String> requiredLicenses,
                       boolean militarySupport, String etc, String preferentialTreatment, List<ProgressType> hiringProgress, Integer requiredGrade
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

    public void checkCompany(Long companyId) {
        if (!this.company.getId().equals(companyId)) {
            throw CompanyMismatchException.EXCEPTION;
        }
    }

    public void checkIsApplicatable() {
        if (this.status != RecruitStatus.RECRUITING) {
            throw InvalidRecruitmentStatusException.EXCEPTION;
        }
    }
}
