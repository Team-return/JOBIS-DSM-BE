package com.example.jobisapplication.domain.acceptance.usecase;

import com.example.jobisapplication.common.annotation.UseCase;
import com.example.jobisapplication.domain.acceptance.dto.request.CancelFieldTraineesRequest;
import com.example.jobisapplication.domain.application.model.Application;
import com.example.jobisapplication.domain.application.spi.CommandApplicationPort;
import com.example.jobisapplication.domain.application.spi.QueryApplicationPort;
import lombok.RequiredArgsConstructor;
import com.example.jobisapplication.domain.application.model.ApplicationStatus;
import com.example.jobisapplication.domain.application.exception.ApplicationNotFoundException;

import java.util.List;

@RequiredArgsConstructor
@UseCase
public class CancelFieldTraineesUseCase {

    private final QueryApplicationPort applicationRepository;
    private final CommandApplicationPort commandApplicationPort;

    public void execute(CancelFieldTraineesRequest request) {
        List<Application> applications = applicationRepository.queryApplicationsByIds(request.getApplicationIds());

        if (request.getApplicationIds().size() != applications.size()) {
            throw ApplicationNotFoundException.EXCEPTION;
        }

        applications.forEach(application ->
                application.checkApplicationStatus(application.getApplicationStatus(), ApplicationStatus.FIELD_TRAIN)
        );

        commandApplicationPort.changeApplicationStatus(
                ApplicationStatus.PASS,
                request.getApplicationIds()
        );
    }
}
