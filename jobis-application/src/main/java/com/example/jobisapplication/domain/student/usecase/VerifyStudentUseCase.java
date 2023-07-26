package com.example.jobisapplication.domain.student.usecase;

import com.example.jobisapplication.common.annotation.UseCase;
import com.example.jobisapplication.domain.student.spi.QueryVerifiedStudentPort;
import lombok.RequiredArgsConstructor;
import com.example.jobisapplication.domain.student.exception.StudentNotFoundException;

@RequiredArgsConstructor
@UseCase
public class VerifyStudentUseCase {

    private final QueryVerifiedStudentPort queryVerifiedStudentPort;

    public void execute(String gcn, String name) {
        if (!queryVerifiedStudentPort.existsVerifiedStudentByGcnAndName(gcn, name)) {
            throw StudentNotFoundException.EXCEPTION;
        }
    }
}
