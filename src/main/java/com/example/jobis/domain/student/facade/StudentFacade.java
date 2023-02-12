package com.example.jobis.domain.student.facade;

import com.example.jobis.domain.student.domain.Student;
import com.example.jobis.domain.student.domain.repository.StudentJpaRepository;
import com.example.jobis.domain.student.exception.StudentNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class StudentFacade {

    private final StudentJpaRepository studentJpaRepository;

    public boolean existsEmail(String email) {
        return studentJpaRepository.existsByEmail(email);
    }

    public Student getCurrentStudent() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return studentJpaRepository.findByEmail(email)
                .orElseThrow(() -> StudentNotFoundException.EXCEPTION);
    }
}
