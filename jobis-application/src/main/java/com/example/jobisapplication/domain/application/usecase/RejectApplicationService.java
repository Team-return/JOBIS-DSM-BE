package com.example.jobisapplication.domain.application.usecase;

import com.example.jobisapplication.common.annotation.UseCase;
import com.example.jobisapplication.domain.application.model.Application;
import com.example.jobisapplication.domain.application.spi.CommandApplicationPort;
import com.example.jobisapplication.domain.application.spi.QueryApplicationPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
public class RejectApplicationService {

    private final QueryApplicationPort applicationPersistenceAdapter;
    private final CommandApplicationPort commandApplicationPort;

    public void execute(Long applicationId, String rejectReason) {
        Application application = applicationPersistenceAdapter.queryApplicationById(applicationId);

        commandApplicationPort.saveApplication(
                application.rejectApplication(rejectReason)
        );
    }
}
