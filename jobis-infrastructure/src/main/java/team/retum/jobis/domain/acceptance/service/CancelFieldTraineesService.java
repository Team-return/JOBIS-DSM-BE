package team.retum.jobis.domain.acceptance.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.acceptance.presentation.dto.request.CancelFieldTraineesWebRequest;
import team.retum.jobis.domain.application.persistence.entity.ApplicationEntity;
import com.example.jobisapplication.domain.application.model.ApplicationStatus;
import team.retum.jobis.domain.application.persistence.repository.ApplicationRepository;
import com.example.jobisapplication.domain.application.exception.ApplicationNotFoundException;
import com.example.jobisapplication.common.annotation.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CancelFieldTraineesService {

    private final ApplicationRepository applicationRepository;

    public void execute(CancelFieldTraineesWebRequest request) {
        List<ApplicationEntity> applicationEntities = applicationRepository.queryApplicationsByIds(request.getApplicationIds());

        if (request.getApplicationIds().size() != applicationEntities.size()) {
            throw ApplicationNotFoundException.EXCEPTION;
        }

        applicationEntities.forEach(application ->
                application.checkApplicationStatus(application.getApplicationStatus(), ApplicationStatus.FIELD_TRAIN)
        );

        applicationRepository.changeApplicationStatus(
                ApplicationStatus.PASS,
                applicationEntities
        );
    }
}
