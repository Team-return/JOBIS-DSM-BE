package com.example.jobisapplication.domain.student.spi;

import com.example.jobisapplication.domain.student.model.Student;

import java.util.Optional;

public interface QueryStudentPort {
    Optional<Student> queryStudentById(Long studentId);

    boolean existsByGradeAndClassRoomAndNumber(int grade, int classRoom, int number);
}
