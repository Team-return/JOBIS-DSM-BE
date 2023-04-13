package team.returm.jobis.domain.application.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.application.domain.Application;
import team.returm.jobis.domain.application.domain.enums.ApplicationStatus;
import team.returm.jobis.domain.application.domain.repository.ApplicationRepository;
import team.returm.jobis.domain.application.exception.ApplicationNotFoundException;
import team.returm.jobis.domain.recruitment.domain.repository.RecruitmentRepository;
import team.returm.jobis.global.annotation.Service;

@RequiredArgsConstructor
@Service
public class ChangeApplicationsStatusService {

    private final ApplicationRepository applicationRepository;
    private final RecruitmentRepository recruitmentRepository;

    public void execute(List<Long> applicationIds, ApplicationStatus status) {
        List<Application> applications = applicationRepository.queryApplicationByIds(applicationIds);

        if (applicationIds.size() != applications.size()) {
            throw ApplicationNotFoundException.EXCEPTION;
        }

        List<Application> filteredApplications = applications.stream()
                .filter(application ->
                        !application.getApplicationStatus().equals(status)
                ).toList();
        List<Long> recruitmentIds = recruitmentRepository.queryRecruitmentsByApplications(filteredApplications);

        if (status.equals(ApplicationStatus.REQUESTED)) {
            recruitmentRepository.addApplicationRequestedCount(recruitmentIds);
        } else {
            recruitmentRepository.addApplicationApprovedCount(recruitmentIds);
        }

            applicationRepository.changeApplicationStatus(status, applications);
    }
}
