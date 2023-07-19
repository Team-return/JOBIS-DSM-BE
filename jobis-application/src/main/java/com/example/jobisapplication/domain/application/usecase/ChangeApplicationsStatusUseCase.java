package com.example.jobisapplication.domain.application.usecase;

import com.example.jobisapplication.common.annotation.UseCase;
import com.example.jobisapplication.domain.application.model.Application;
import com.example.jobisapplication.domain.application.spi.CommandApplicationPort;
import com.example.jobisapplication.domain.application.spi.QueryApplicationPort;
import lombok.RequiredArgsConstructor;
import com.example.jobisapplication.domain.application.model.ApplicationStatus;
import com.example.jobisapplication.domain.application.exception.ApplicationNotFoundException;

import java.util.List;

@RequiredArgsConstructor
@UseCase
public class ChangeApplicationsStatusUseCase {

    private final QueryApplicationPort queryApplicationPort;
    private final CommandApplicationPort commandApplicationPort;

    public void execute(List<Long> applicationIds, ApplicationStatus status) {
        List<Application> applications = queryApplicationPort.queryApplicationsByIds(applicationIds);

        if (applicationIds.size() != applications.size()) {
            throw ApplicationNotFoundException.EXCEPTION;
        }

        commandApplicationPort.changeApplicationStatus(status, applicationIds);
    }
}
