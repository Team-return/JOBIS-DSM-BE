package team.returm.jobis.domain.application.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.returm.jobis.domain.application.domain.enums.ApplicationStatus;
import team.returm.jobis.domain.recruitment.domain.Recruitment;
import team.returm.jobis.domain.student.domain.Student;
import team.returm.jobis.global.entity.BaseTimeEntity;

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
        this.applicationStatus = ApplicationStatus.FIELD_TRAIN;
        this.startDate = startDate;
        this.endDate = endDate;

        return this;
    }
}
