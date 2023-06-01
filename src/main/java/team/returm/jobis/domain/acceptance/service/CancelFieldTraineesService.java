package team.returm.jobis.domain.acceptance.service;

import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.acceptance.presentation.dto.request.CancelFieldTraineesRequest;
import team.returm.jobis.domain.application.domain.Application;
import team.returm.jobis.domain.application.domain.enums.ApplicationStatus;
import team.returm.jobis.domain.application.domain.repository.ApplicationRepository;
import team.returm.jobis.domain.application.exception.ApplicationNotFoundException;
import team.returm.jobis.global.annotation.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CancelFieldTraineesService {

    private final ApplicationRepository applicationRepository;

    public void execute(CancelFieldTraineesRequest request) {
        List<Application> applications = applicationRepository.queryApplicationsByIds(request.getApplicationIds());

        if (request.getApplicationIds().size() != applications.size()) {
            throw ApplicationNotFoundException.EXCEPTION;
        }

        applicationRepository.changeApplicationStatus(
                ApplicationStatus.PASS,
                applications.stream()
                        .map(application -> {
                            application.checkApplicationsStatus(
                                    application.getApplicationStatus(), ApplicationStatus.FIELD_TRAIN
                            );

                            return application;
                        }).toList()
        );
    }
}
