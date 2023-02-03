package com.example.jobis.domain.application.domain;

import com.example.jobis.domain.company.domain.Company;
import com.example.jobis.domain.student.domain.Student;
import com.example.jobis.global.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Application extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "application", orphanRemoval = true)
    private List<ApplicationAttachment> applicationAttachments = new ArrayList<>();

    @Builder
    public Application(Student student, Company company) {
        this.student = student;
        this.company = company;
    }
}
