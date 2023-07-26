package com.example.jobisapplication.domain.student.usecase;

import com.example.jobisapplication.common.annotation.ReadOnlyUseCase;
import com.example.jobisapplication.common.spi.SecurityPort;
import com.example.jobisapplication.domain.student.exception.StudentNotFoundException;
import com.example.jobisapplication.domain.student.model.Student;
import com.example.jobisapplication.domain.student.spi.QueryStudentPort;
import lombok.RequiredArgsConstructor;
import com.example.jobisapplication.domain.student.dto.StudentMyPageResponse;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class StudentMyPageUseCase {

    private final SecurityPort securityPort;
    private final QueryStudentPort queryStudentPort;

    public StudentMyPageResponse execute() {
        Long currentUserId = securityPort.getCurrentUserId();
        Student student = queryStudentPort.queryStudentById(currentUserId)
                .orElseThrow(() -> StudentNotFoundException.EXCEPTION);

        return StudentMyPageResponse.of(student);
    }
}
