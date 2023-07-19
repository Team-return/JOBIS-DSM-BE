package com.example.jobisapplication.domain.application.usecase;

import com.example.jobisapplication.common.annotation.UseCase;
import com.example.jobisapplication.domain.application.dto.request.ChangeFieldTrainDateRequest;
import com.example.jobisapplication.domain.application.model.Application;
import com.example.jobisapplication.domain.application.spi.CommandApplicationPort;
import com.example.jobisapplication.domain.application.spi.QueryApplicationPort;
import lombok.RequiredArgsConstructor;
import com.example.jobisapplication.domain.application.model.ApplicationStatus;
import com.example.jobisapplication.domain.application.exception.InvalidDateException;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@UseCase
public class ChangeFieldTrainDateService {

    private final QueryApplicationPort queryApplicationPort;
    private final CommandApplicationPort commandApplicationPort;

    public void execute(ChangeFieldTrainDateRequest request) {

        if (request.getStartDate().isBefore(LocalDate.now())) {
            throw InvalidDateException.EXCEPTION;
        }

        List<Application> applications = queryApplicationPort.queryApplicationsByIds(request.getApplicationIds());

        applications
                .forEach(application ->
                        application.checkApplicationStatus(application.getApplicationStatus(), ApplicationStatus.FIELD_TRAIN)
                );

        commandApplicationPort.updateFieldTrainDate(
                request.getStartDate(),
                request.getEndDate(),
                request.getApplicationIds()
        );
    }
}
