package team.retum.jobis.domain.application.persistence;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.example.jobisapplication.domain.application.domain.ApplicationStatus;
import team.retum.jobis.domain.application.exception.ApplicationCannotDeleteException;
import team.retum.jobis.domain.application.exception.ApplicationNotFoundException;
import team.retum.jobis.domain.application.exception.ApplicationStatusCannotChangeException;
import team.retum.jobis.domain.application.exception.InvalidStudentException;
import team.retum.jobis.domain.recruitment.persistence.RecruitmentEntity;
import team.retum.jobis.domain.student.persistence.StudentEntity;
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
    private StudentEntity studentEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruitment_id")
    private RecruitmentEntity recruitmentEntity;

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
    private List<ApplicationAttachmentEntity> applicationAttachmentEntities = new ArrayList<>();

    @Builder
    public ApplicationEntity(StudentEntity studentEntity, RecruitmentEntity recruitmentEntity, ApplicationStatus applicationStatus) {
        this.studentEntity = studentEntity;
        this.recruitmentEntity = recruitmentEntity;
        this.applicationStatus = applicationStatus;
    }

    public ApplicationEntity toFieldTrain(LocalDate startDate, LocalDate endDate) {
        checkApplicationStatus(this.applicationStatus, ApplicationStatus.PASS);

        this.applicationStatus = ApplicationStatus.FIELD_TRAIN;
        this.startDate = startDate;
        this.endDate = endDate;

        return this;
    }

    public void rejectApplication(String reason) {
        if (applicationStatus != ApplicationStatus.REQUESTED) {
            throw ApplicationStatusCannotChangeException.EXCEPTION;
        }

        this.applicationStatus = ApplicationStatus.REJECTED;
        this.rejectionReason = reason;
    }

    public void checkReviewAuthority() {
        if (this.applicationStatus == ApplicationStatus.REQUESTED
                || this.applicationStatus == ApplicationStatus.APPROVED
                || this.applicationStatus == ApplicationStatus.REJECTED) {
            throw ApplicationNotFoundException.EXCEPTION;
        }
    }

    public void checkApplicationStatus(ApplicationStatus status1, ApplicationStatus status2) {
        if (status1 != status2) {
            throw ApplicationStatusCannotChangeException.EXCEPTION;
        }
    }

    public void checkIsDeleteable(StudentEntity studentEntity) {
        if (!this.studentEntity.equals(studentEntity)) {
            throw InvalidStudentException.EXCEPTION;
        }

        if (this.applicationStatus != ApplicationStatus.REQUESTED) {
            throw ApplicationCannotDeleteException.EXCEPTION;
        }
    }
}
