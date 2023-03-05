package com.example.jobis.domain.application.domain;

import com.example.jobis.domain.application.domain.enums.ApplicationStatus;
import com.example.jobis.domain.recruitment.domain.Recruitment;
import com.example.jobis.domain.student.domain.Student;
import com.example.jobis.global.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
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
