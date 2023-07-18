package team.retum.jobis.domain.application.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.application.persistence.Application;
import team.retum.jobis.domain.application.persistence.repository.ApplicationRepository;
import team.retum.jobis.domain.application.exception.ApplicationNotFoundException;
import team.retum.jobis.global.annotation.Service;

@RequiredArgsConstructor
@Service
public class RejectApplicationService {

    private final ApplicationRepository applicationRepository;

    public void execute(Long applicationId, String rejectReason) {
        Application application = applicationRepository.queryApplicationById(applicationId)
                .orElseThrow(() -> ApplicationNotFoundException.EXCEPTION);

        application.rejectApplication(rejectReason);
    }
}
