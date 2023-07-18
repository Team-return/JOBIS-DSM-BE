package team.retum.jobis.domain.acceptance.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.acceptance.presentation.dto.request.CancelFieldTraineesRequest;
import team.retum.jobis.domain.application.persistence.Application;
import team.retum.jobis.domain.application.persistence.enums.ApplicationStatus;
import team.retum.jobis.domain.application.persistence.repository.ApplicationRepository;
import team.retum.jobis.domain.application.exception.ApplicationNotFoundException;
import com.example.jobisapplication.common.annotation.Service;

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

        applications.forEach(application ->
                application.checkApplicationStatus(application.getApplicationStatus(), ApplicationStatus.FIELD_TRAIN)
        );

        applicationRepository.changeApplicationStatus(
                ApplicationStatus.PASS,
                applications
        );
    }
}
