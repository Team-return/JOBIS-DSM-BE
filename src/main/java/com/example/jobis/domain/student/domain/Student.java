package com.example.jobis.domain.student.domain;

import com.example.jobis.domain.student.domain.types.Gender;
import com.example.jobis.domain.student.domain.types.Grade;
import com.example.jobis.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Student {

    @Id
    @Column(name = "student_id")
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private User user;

    @Column(length = 10, nullable = false)
    private String name;

    @NotNull
    @Column(columnDefinition = "VARCHAR(30)")
    private String email;

    @NotNull
    @Column(columnDefinition = "VARCHAR(12)")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Grade grade;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Builder
    public Student(User user, String name, Grade grade, Gender gender, String email, String phoneNumber) {
        this.user = user;
        this.name = name;
        this.grade = grade;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
