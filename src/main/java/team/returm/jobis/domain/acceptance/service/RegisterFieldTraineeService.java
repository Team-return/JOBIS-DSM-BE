package team.returm.jobis.domain.acceptance.service;

import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.application.domain.Application;
import team.returm.jobis.domain.application.domain.enums.ApplicationStatus;
import team.returm.jobis.domain.application.domain.repository.ApplicationRepository;
import team.returm.jobis.domain.application.exception.ApplicationNotFoundException;
import team.returm.jobis.domain.application.exception.ApplicationStatusCannotChangeException;
import team.returm.jobis.global.annotation.Service;

@Service
@RequiredArgsConstructor
public class RegisterFieldTraineeService {

    private final ApplicationRepository applicationRepository;

    public void execute(Long applicationId, LocalDate startDate, LocalDate endDate) {
        Application application = applicationRepository.queryApplicationById(applicationId)
                .orElseThrow(() -> ApplicationNotFoundException.EXCEPTION);

        if (application.getApplicationStatus() != ApplicationStatus.PASS) {
            throw ApplicationStatusCannotChangeException.EXCEPTION;
        }

        applicationRepository.saveApplication(
                application.toFieldTrain(startDate, endDate)
        );
    }
}
