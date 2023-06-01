package team.returm.jobis.domain.acceptance.service;

import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.acceptance.presentation.dto.request.RegisterFieldTraineeRequest;
import team.returm.jobis.domain.application.domain.Application;
import team.returm.jobis.domain.application.domain.repository.ApplicationRepository;
import team.returm.jobis.domain.application.exception.ApplicationNotFoundException;
import team.returm.jobis.global.annotation.Service;

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
