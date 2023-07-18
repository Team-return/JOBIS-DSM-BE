package team.retum.jobis.domain.application.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.application.persistence.Application;
import team.retum.jobis.domain.application.persistence.enums.ApplicationStatus;
import team.retum.jobis.domain.application.persistence.repository.ApplicationRepository;
import team.retum.jobis.domain.application.exception.InvalidDateException;
import team.retum.jobis.domain.application.presentation.dto.request.ChangeFieldTrainDateRequest;
import com.example.jobisapplication.common.annotation.Service;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ChangeFieldTrainDateService {

    private final ApplicationRepository applicationRepository;

    public void execute(ChangeFieldTrainDateRequest request) {

        if (request.getStartDate().isBefore(LocalDate.now())) {
            throw InvalidDateException.EXCEPTION;
        }

        List<Application> applications = applicationRepository.queryApplicationsByIds(request.getApplicationIds());

        applications
                .forEach(application ->
                        application.checkApplicationStatus(application.getApplicationStatus(), ApplicationStatus.FIELD_TRAIN)
                );

        applicationRepository.updateFieldTrainDate(
                request.getStartDate(),
                request.getEndDate(),
                applications
        );
    }
}
