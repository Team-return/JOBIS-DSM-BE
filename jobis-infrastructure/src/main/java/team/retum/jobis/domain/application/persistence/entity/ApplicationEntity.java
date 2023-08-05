package team.retum.jobis.domain.application.persistence.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.application.model.ApplicationStatus;
import team.retum.jobis.domain.recruitment.persistence.entity.RecruitmentEntity;
import team.retum.jobis.domain.student.persistence.entity.StudentEntity;
import team.retum.jobis.global.entity.BaseTimeEntity;

import javax.persistence.Column;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_application")
@Entity
public class ApplicationEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private StudentEntity student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruitment_id")
    private RecruitmentEntity recruitment;

    @NotNull
    @Column(columnDefinition = "VARCHAR(11)")
    @Enumerated(EnumType.STRING)
    private ApplicationStatus applicationStatus;

    @Column(columnDefinition = "VARCHAR(100)")
    private String rejectionReason;

    @Column(columnDefinition = "DATE")
    private LocalDate startDate;

    @Column(columnDefinition = "DATE")
    private LocalDate endDate;

    @OneToMany(mappedBy = "application", orphanRemoval = true)
    private List<ApplicationAttachmentEntity> applicationAttachments = new ArrayList<>();

    @Builder
    public ApplicationEntity(Long id, StudentEntity studentEntity, RecruitmentEntity recruitmentEntity,
                             ApplicationStatus applicationStatus, String rejectionReason,
                             LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.student = studentEntity;
        this.recruitment = recruitmentEntity;
        this.applicationStatus = applicationStatus;
        this.rejectionReason = rejectionReason;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
