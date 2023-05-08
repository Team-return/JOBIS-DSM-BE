package team.returm.jobis.domain.application.service;

import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.application.domain.Application;
import team.returm.jobis.domain.application.domain.enums.ApplicationStatus;
import team.returm.jobis.domain.application.domain.repository.ApplicationRepository;
import team.returm.jobis.domain.application.exception.FieldTrainDateCannotChangeException;
import team.returm.jobis.domain.application.exception.InvalidDateException;
import team.returm.jobis.domain.application.presentation.dto.request.ChangeFieldTrainDateRequest;
import team.returm.jobis.global.annotation.Service;

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

        List<Application> fieldTrainApplications =
                applicationRepository.queryApplicationsByStudentIds(request.getStudentIds());

        boolean isFieldTrainsExist = fieldTrainApplications.stream()
                .allMatch(application ->
                        application.getApplicationStatus() == ApplicationStatus.FIELD_TRAIN);

        if (!isFieldTrainsExist) {
            throw FieldTrainDateCannotChangeException.EXCEPTION;
        }

        applicationRepository.updateFieldTrainDate(
                request.getStartDate(),
                request.getEndDate(),
                fieldTrainApplications
        );
    }
}
