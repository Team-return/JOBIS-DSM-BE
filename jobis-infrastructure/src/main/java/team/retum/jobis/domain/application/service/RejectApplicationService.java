package team.retum.jobis.domain.application.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.application.persistence.ApplicationEntity;
import team.retum.jobis.domain.application.persistence.repository.ApplicationRepository;
import team.retum.jobis.domain.application.exception.ApplicationNotFoundException;
import com.example.jobisapplication.common.annotation.Service;

@RequiredArgsConstructor
@Service
public class RejectApplicationService {

    private final ApplicationRepository applicationRepository;

    public void execute(Long applicationId, String rejectReason) {
        ApplicationEntity applicationEntity = applicationRepository.queryApplicationById(applicationId)
                .orElseThrow(() -> ApplicationNotFoundException.EXCEPTION);

        applicationEntity.rejectApplication(rejectReason);
    }
}
