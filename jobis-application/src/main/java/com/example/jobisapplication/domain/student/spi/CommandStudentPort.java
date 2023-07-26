package com.example.jobisapplication.domain.student.spi;

import com.example.jobisapplication.domain.student.model.Student;

public interface CommandStudentPort {
    Student saveStudent(Student student);
}
