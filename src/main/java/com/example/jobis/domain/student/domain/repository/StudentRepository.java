package com.example.jobis.domain.student.domain.repository;

import com.example.jobis.domain.student.domain.Student;
import com.example.jobis.domain.student.exception.StudentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class StudentRepository {
    private final StudentJpaRepository studentJpaRepository;

    public Student findByEmail(String email) {
        return studentJpaRepository.findByEmail(email)
                .orElseThrow(() -> StudentNotFoundException.EXCEPTION);
    }
}
