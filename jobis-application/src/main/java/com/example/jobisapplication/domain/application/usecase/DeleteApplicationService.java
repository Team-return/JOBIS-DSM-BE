package com.example.jobisapplication.domain.application.usecase;

import com.example.jobisapplication.common.annotation.UseCase;
import com.example.jobisapplication.common.spi.SecurityPort;
import com.example.jobisapplication.domain.application.model.Application;
import com.example.jobisapplication.domain.application.spi.CommandApplicationPort;
import com.example.jobisapplication.domain.application.spi.QueryApplicationPort;
import com.example.jobisapplication.domain.student.exception.StudentNotFoundException;
import com.example.jobisapplication.domain.student.model.Student;
import com.example.jobisapplication.domain.student.spi.QueryStudentPort;
import lombok.RequiredArgsConstructor;
import com.example.jobisapplication.domain.application.exception.ApplicationNotFoundException;

@RequiredArgsConstructor
@UseCase
public class DeleteApplicationService {

    private final CommandApplicationPort commandApplicationPort;
    private final QueryApplicationPort queryApplicationPort;
    private final SecurityPort securityPort;
    private final QueryStudentPort queryStudentPort;

    public void execute(Long applicationId) {
        Long currentUserId = securityPort.getCurrentUserId();
        Student student = queryStudentPort.queryStudentById(currentUserId)
                .orElseThrow(() -> StudentNotFoundException.EXCEPTION);

        Application application = queryApplicationPort.queryApplicationById(applicationId);
        application.checkIsDeletable(student);

        commandApplicationPort.deleteApplication(application);
    }
}
