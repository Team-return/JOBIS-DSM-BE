package com.example.jobis.domain.student.facade;

import com.example.jobis.domain.student.domain.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class StudentFacade {

    private final StudentRepository studentRepository;

    public boolean existsEmail(String email) {
        return studentRepository.existsByAccountId(email);
    }
}
