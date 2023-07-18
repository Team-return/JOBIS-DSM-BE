package team.retum.jobis.domain.acceptance.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.acceptance.presentation.dto.request.RegisterFieldTraineeRequest;
import team.retum.jobis.domain.application.persistence.entity.ApplicationEntity;
import team.retum.jobis.domain.application.persistence.repository.ApplicationRepository;
import com.example.jobisapplication.domain.application.exception.ApplicationNotFoundException;
import com.example.jobisapplication.common.annotation.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegisterFieldTraineeService {

    private final ApplicationRepository applicationRepository;

    public void execute(RegisterFieldTraineeRequest request) {
        List<ApplicationEntity> applicationEntities = applicationRepository.queryApplicationsByIds(request.getApplicationIds());

        if (request.getApplicationIds().size() != applicationEntities.size()) {
            throw ApplicationNotFoundException.EXCEPTION;
        }

        applicationRepository.saveAllApplications(
                applicationEntities.stream()
                        .map(
                                application -> application.toFieldTrain(request.getStartDate(), request.getEndDate())
                        ).toList()
        );
    }
}
