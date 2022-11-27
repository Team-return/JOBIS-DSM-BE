package com.example.jobis.domain.student.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Portfolio {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(nullable = false)
    private String portfolio;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;


    @Builder
    public Portfolio(String portfolio, Student student) {
        this.portfolio = portfolio;
        this.student = student;
    }
}
