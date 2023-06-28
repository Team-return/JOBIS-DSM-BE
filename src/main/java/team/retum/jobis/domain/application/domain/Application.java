package team.retum.jobis.domain.application.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.application.domain.enums.ApplicationStatus;
import team.retum.jobis.domain.application.exception.ApplicationCannotDeleteException;
import team.retum.jobis.domain.application.exception.ApplicationNotFoundException;
import team.retum.jobis.domain.application.exception.ApplicationStatusCannotChangeException;
import team.retum.jobis.domain.application.exception.InvalidStudentException;
import team.retum.jobis.domain.recruitment.domain.Recruitment;
import team.retum.jobis.domain.student.domain.Student;
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
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Application extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruitment_id")
    private Recruitment recruitment;

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
    private List<ApplicationAttachment> applicationAttachments = new ArrayList<>();

    @Builder
    public Application(Student student, Recruitment recruitment, ApplicationStatus applicationStatus) {
        this.student = student;
        this.recruitment = recruitment;
        this.applicationStatus = applicationStatus;
    }

    public Application toFieldTrain(LocalDate startDate, LocalDate endDate) {
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

    public void checkIsDeleteable(Student student) {
        if (!this.student.equals(student)) {
            throw InvalidStudentException.EXCEPTION;
        }

        if (this.applicationStatus != ApplicationStatus.REQUESTED) {
            throw ApplicationCannotDeleteException.EXCEPTION;
        }
    }
}
