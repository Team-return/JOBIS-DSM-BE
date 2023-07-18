package team.retum.jobis.domain.application.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.application.persistence.entity.ApplicationEntity;
import com.example.jobisapplication.domain.application.domain.ApplicationStatus;
import team.retum.jobis.domain.application.persistence.repository.ApplicationRepository;
import team.retum.jobis.domain.application.exception.ApplicationNotFoundException;
import com.example.jobisapplication.common.annotation.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ChangeApplicationsStatusService {

    private final ApplicationRepository applicationRepository;

    public void execute(List<Long> applicationIds, ApplicationStatus status) {
        List<ApplicationEntity> applicationEntities = applicationRepository.queryApplicationsByIds(applicationIds);

        if (applicationIds.size() != applicationEntities.size()) {
            throw ApplicationNotFoundException.EXCEPTION;
        }

        applicationRepository.changeApplicationStatus(status, applicationEntities);
    }
}
