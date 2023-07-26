package com.example.jobisapplication.domain.student.usecase;

import com.example.jobisapplication.common.annotation.UseCase;
import com.example.jobisapplication.common.spi.SecurityPort;
import com.example.jobisapplication.domain.student.exception.StudentNotFoundException;
import com.example.jobisapplication.domain.student.model.Student;
import com.example.jobisapplication.domain.student.spi.CommandStudentPort;
import com.example.jobisapplication.domain.student.spi.QueryStudentPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
public class UpdateStudentProfileUseCase {

    private final SecurityPort securityPort;
    private final QueryStudentPort queryStudentPort;
    private final CommandStudentPort commandStudentPort;

    public void execute(String profileImageUrl) {
        Long currentUserId = securityPort.getCurrentUserId();
        Student student = queryStudentPort.queryStudentById(currentUserId)
                        .orElseThrow(() -> StudentNotFoundException.EXCEPTION);

        commandStudentPort.saveStudent(
                student.changeStudentProfile(profileImageUrl)
        );
    }
}
