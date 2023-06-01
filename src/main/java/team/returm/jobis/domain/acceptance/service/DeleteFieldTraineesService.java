package team.returm.jobis.domain.acceptance.service;

import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.acceptance.presentation.dto.request.DeleteFieldTraineesRequest;
import team.returm.jobis.domain.application.domain.Application;
import team.returm.jobis.domain.application.domain.enums.ApplicationStatus;
import team.returm.jobis.domain.application.domain.repository.ApplicationRepository;
import team.returm.jobis.domain.application.exception.ApplicationCannotDeleteException;
import team.returm.jobis.domain.application.exception.ApplicationNotFoundException;
import team.returm.jobis.global.annotation.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DeleteFieldTraineesService {

    private final ApplicationRepository applicationRepository;

    public void execute(DeleteFieldTraineesRequest request) {

        List<Application> applications = applicationRepository.queryApplicationsByIds(request.getApplicationIds());

        if (request.getApplicationIds().size() != applications.size()) {
            throw ApplicationNotFoundException.EXCEPTION;
        }

        if (!applications.stream()
                .anyMatch(application -> application.getApplicationStatus() == ApplicationStatus.FIELD_TRAIN)) {
            throw ApplicationCannotDeleteException.EXCEPTION;
        }

        applicationRepository.changeApplicationStatus(ApplicationStatus.PASS, applications);
    }
}
