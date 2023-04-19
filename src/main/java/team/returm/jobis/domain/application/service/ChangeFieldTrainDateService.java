package team.returm.jobis.domain.application.service;

import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.application.domain.Application;
import team.returm.jobis.domain.application.domain.repository.ApplicationRepository;
import team.returm.jobis.domain.application.presentation.dto.request.ChangeFieldTrainDateRequest;
import team.returm.jobis.global.annotation.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ChangeFieldTrainDateService {

    private final ApplicationRepository applicationRepository;

    public void execute(ChangeFieldTrainDateRequest request) {

        List<Application> applications =
                applicationRepository.queryApplicationsByStudentIds(request.getStudentIds());

        applicationRepository.changeFieldTrainDate(
                request.getStartDate(),
                request.getEndDate(),
                applications
        );
    }
}
