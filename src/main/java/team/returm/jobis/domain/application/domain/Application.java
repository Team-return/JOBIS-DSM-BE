package team.returm.jobis.domain.application.domain;

import team.returm.jobis.domain.application.domain.enums.ApplicationStatus;
import team.returm.jobis.domain.recruitment.domain.Recruitment;
import team.returm.jobis.domain.student.domain.Student;
import team.returm.jobis.global.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Application extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruitment_id")
    private Recruitment recruitment;

    @Column(columnDefinition = "VARCHAR(9)", nullable = false)
    @Enumerated(EnumType.STRING)
    private ApplicationStatus applicationStatus;

    @OneToMany(mappedBy = "application", orphanRemoval = true)
    private List<ApplicationAttachment> applicationAttachments = new ArrayList<>();

    @Builder
    public Application(Student student, Recruitment recruitment, ApplicationStatus applicationStatus) {
        this.student = student;
        this.recruitment = recruitment;
        this.applicationStatus = applicationStatus;
    }
}
