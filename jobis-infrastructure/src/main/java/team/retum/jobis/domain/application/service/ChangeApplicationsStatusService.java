package team.retum.jobis.domain.application.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.application.domain.Application;
import team.retum.jobis.domain.application.domain.enums.ApplicationStatus;
import team.retum.jobis.domain.application.domain.repository.ApplicationRepository;
import team.retum.jobis.domain.application.exception.ApplicationNotFoundException;
import team.retum.jobis.global.annotation.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ChangeApplicationsStatusService {

    private final ApplicationRepository applicationRepository;

    public void execute(List<Long> applicationIds, ApplicationStatus status) {
        List<Application> applications = applicationRepository.queryApplicationsByIds(applicationIds);

        if (applicationIds.size() != applications.size()) {
            throw ApplicationNotFoundException.EXCEPTION;
        }

        applicationRepository.changeApplicationStatus(status, applications);
    }
}
