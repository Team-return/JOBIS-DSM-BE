package com.example.jobisapplication.domain.acceptance.usecase;

import com.example.jobisapplication.common.annotation.UseCase;
import com.example.jobisapplication.domain.acceptance.dto.request.RegisterFieldTraineeRequest;
import com.example.jobisapplication.domain.application.model.Application;
import com.example.jobisapplication.domain.application.spi.CommandApplicationPort;
import com.example.jobisapplication.domain.application.spi.QueryApplicationPort;
import lombok.RequiredArgsConstructor;
import com.example.jobisapplication.domain.application.exception.ApplicationNotFoundException;

import java.util.List;

@RequiredArgsConstructor
@UseCase
public class RegisterFieldTraineeService {

    private final CommandApplicationPort commandApplicationPort;
    private final QueryApplicationPort queryApplicationPort;

    public void execute(RegisterFieldTraineeRequest request) {
        List<Application> applications = queryApplicationPort.queryApplicationsByIds(request.getApplicationIds());

        if (request.getApplicationIds().size() != applications.size()) {
            throw ApplicationNotFoundException.EXCEPTION;
        }

        List<Application> converted = applications.stream()
                        .map( application ->
                                application.toFieldTrain(request.getStartDate(), request.getEndDate())
                        ).toList();

        commandApplicationPort.saveAllApplications(converted);
    }
}
