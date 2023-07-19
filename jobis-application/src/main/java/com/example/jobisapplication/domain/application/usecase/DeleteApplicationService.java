package com.example.jobisapplication.domain.application.usecase;

import com.example.jobisapplication.common.annotation.UseCase;
import com.example.jobisapplication.domain.application.model.Application;
import com.example.jobisapplication.domain.application.spi.CommandApplicationPort;
import com.example.jobisapplication.domain.application.spi.QueryApplicationPort;
import com.example.jobisapplication.domain.student.model.Student;
import lombok.RequiredArgsConstructor;
import com.example.jobisapplication.domain.application.exception.ApplicationNotFoundException;

@RequiredArgsConstructor
@UseCase
public class DeleteApplicationService {

    private final CommandApplicationPort commandApplicationPort;
    private final QueryApplicationPort queryApplicationPort;
    private final UserFacade userFacade;

    public void execute(Long applicationId) {
        Student student = userFacade.getCurrentStudent();

        Application application = queryApplicationPort.queryApplicationById(applicationId);
        application.checkIsDeletable(student);

        commandApplicationPort.deleteApplication(application);
    }
}
