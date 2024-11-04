package team.retum.jobis.domain.recruitment.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import team.retum.jobis.domain.company.persistence.entity.CompanyEntity;
import team.retum.jobis.domain.recruitment.model.ProgressType;
import team.retum.jobis.domain.recruitment.model.RecruitStatus;
import team.retum.jobis.domain.recruitment.persistence.repository.converter.HiringProgressConverter;
import team.retum.jobis.domain.recruitment.persistence.type.PayInfo;
import team.retum.jobis.domain.recruitment.persistence.type.RecruitDate;
import team.retum.jobis.global.converter.StringListConverter;
import team.retum.jobis.global.entity.BaseTimeEntity;

import java.time.LocalDate;
import java.util.List;

@Getter
@DynamicUpdate
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_recruitment")
@Entity
public class RecruitmentEntity extends BaseTimeEntity {

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

    @Convert(converter = StringListConverter.class)
    @Column(columnDefinition = "VARCHAR(200)")
    private List<String> requiredLicenses;

    @Column(columnDefinition = "VARCHAR(100)")
    private String additionalQualifications;

    @NotNull
    @Column(columnDefinition = "VARCHAR(100)")
    private String workingHours;

    @NotNull
    @Column(columnDefinition = "TINYINT(1)")
    private boolean flexibleWorking;

    @Column(columnDefinition = "VARCHAR(550)")
    private String benefits;

    @Column(columnDefinition = "TINYINT(1)")
    private Boolean militarySupport;

    @NotNull
    @Convert(converter = HiringProgressConverter.class)
    @Column(columnDefinition = "VARCHAR(300)")
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
    @Column(columnDefinition = "TINYINT(1)")
    private boolean personalContact;

    @NotNull
    @Column(columnDefinition = "TINYINT(1)")
    private boolean winterIntern;

    @Column(columnDefinition = "TINYINT(1)")
    private Boolean hireConvertible;

    @Column(columnDefinition = "TINYINT(1)")
    private Boolean integrationPlan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private CompanyEntity company;

    @Builder
    public RecruitmentEntity(Long id, int recruitYear, RecruitStatus status, String workingHours, Integer trainPay, String pay,
                             String submitDocument, LocalDate startDate, LocalDate endDate, CompanyEntity companyEntity,
                             String benefits, Boolean militarySupport, List<String> requiredLicenses, String etc,
                             List<ProgressType> hiringProgress, String additionalQualifications, boolean flexibleWorking,
                             boolean personalContact, boolean winterIntern, Boolean hireConvertible, Boolean integrationPlan) {
        this.id = id;
        this.workingHours = workingHours;
        this.flexibleWorking = flexibleWorking;
        this.hiringProgress = hiringProgress;
        this.submitDocument = submitDocument;
        this.additionalQualifications = additionalQualifications;
        this.recruitYear = recruitYear;
        this.status = status;
        this.benefits = benefits;
        this.personalContact = personalContact;
        this.recruitDate = new RecruitDate(startDate, endDate);
        this.payInfo = new PayInfo(trainPay, pay);
        this.company = companyEntity;
        this.requiredLicenses = requiredLicenses;
        this.militarySupport = militarySupport;
        this.winterIntern = winterIntern;
        this.hireConvertible = hireConvertible;
        this.etc = etc;
        this.integrationPlan = integrationPlan;
    }
}
