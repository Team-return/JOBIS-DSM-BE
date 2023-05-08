package team.returm.jobis.domain.application.service;

import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.application.domain.Application;
import team.returm.jobis.domain.application.domain.enums.ApplicationStatus;
import team.returm.jobis.domain.application.domain.repository.ApplicationRepository;
import team.returm.jobis.domain.application.exception.ApplicationNotFoundException;
import team.returm.jobis.global.annotation.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ChangeApplicationsStatusService {

    private final ApplicationRepository applicationRepository;

    public void execute(List<Long> applicationIds, ApplicationStatus status) {
        List<Application> applications = applicationRepository.queryApplicationByIds(applicationIds);

        if (applicationIds.size() != applications.size()) {
            throw ApplicationNotFoundException.EXCEPTION;
        }

        applicationRepository.changeApplicationStatus(status, applications);
    }
}
