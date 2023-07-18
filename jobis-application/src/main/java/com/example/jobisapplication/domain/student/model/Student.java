package com.example.jobisapplication.domain.student.model;

import com.example.jobisapplication.common.annotation.Aggregate;
import com.example.jobisapplication.domain.application.exception.InvalidGradeException;
import com.example.jobisapplication.domain.student.exception.ClassRoomNotFoundException;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(toBuilder = true)
@Aggregate
public class Student {

    private final Long id;

    private final String name;

    private final Integer grade;

    private final Integer classRoom;

    private final Integer number;

    private final Gender gender;

    private final Department department;

    private final String profileImageUrl;

    public static String processGcn(int grade, int classNumber, int number) {
        return String.valueOf(grade) +
                classNumber +
                (number < 10 ? "0" + number : number);
    }

    public static Department getDepartment(Integer grade, Integer classRoom) {
        if (grade == 1) {
            return Department.COMMON;
        }

        return switch (classRoom) {
            case 1, 2 -> Department.SOFTWARE_DEVELOP;
            case 3 -> Department.EMBEDDED_SOFTWARE;
            case 4 -> Department.INFORMATION_SECURITY;
            default -> throw ClassRoomNotFoundException.EXCEPTION;
        };
    }

    public void checkIs3rdGrade() {
        if (!this.grade.equals(3)) {
            throw InvalidGradeException.EXCEPTION;
        }
    }

    public Student changeStudentProfile(String profileImageUrl) {
        return this.toBuilder()
                .profileImageUrl(profileImageUrl)
                .build();
    }
}
