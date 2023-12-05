package team.retum.jobis.domain.application.persistence.entity;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
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
import team.retum.jobis.domain.application.model.ApplicationStatus;
import team.retum.jobis.domain.recruitment.persistence.entity.RecruitmentEntity;
import team.retum.jobis.domain.student.persistence.entity.StudentEntity;
import team.retum.jobis.global.entity.BaseTimeEntity;

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

    @ElementCollection
    @CollectionTable(name = "tbl_application_attachment", joinColumns = @JoinColumn(name = "application_id"))
    private List<ApplicationAttachmentEntity> attachments = new ArrayList<>();

    @Builder
    public ApplicationEntity(Long id, StudentEntity studentEntity, RecruitmentEntity recruitmentEntity,
                             ApplicationStatus applicationStatus, String rejectionReason,
                             LocalDate startDate, LocalDate endDate, List<ApplicationAttachmentEntity> attachments) {
        this.id = id;
        this.student = studentEntity;
        this.recruitment = recruitmentEntity;
        this.applicationStatus = applicationStatus;
        this.rejectionReason = rejectionReason;
        this.startDate = startDate;
        this.endDate = endDate;
        this.attachments = attachments;
    }
}
