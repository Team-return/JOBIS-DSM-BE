package team.retum.jobis.domain.acceptance.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.acceptance.presentation.dto.request.RegisterFieldTraineeRequest;
import team.retum.jobis.domain.application.domain.Application;
import team.retum.jobis.domain.application.domain.repository.ApplicationRepository;
import team.retum.jobis.domain.application.exception.ApplicationNotFoundException;
import team.retum.jobis.global.annotation.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegisterFieldTraineeService {

    private final ApplicationRepository applicationRepository;

    public void execute(RegisterFieldTraineeRequest request) {
        List<Application> applications = applicationRepository.queryApplicationsByIds(request.getApplicationIds());

        if (request.getApplicationIds().size() != applications.size()) {
            throw ApplicationNotFoundException.EXCEPTION;
        }

        applicationRepository.saveAllApplications(
                applications.stream()
                        .map(
                                application -> application.toFieldTrain(request.getStartDate(), request.getEndDate())
                        ).toList()
        );
    }
}
