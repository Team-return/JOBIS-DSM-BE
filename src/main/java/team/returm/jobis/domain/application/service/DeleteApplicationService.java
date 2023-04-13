package team.returm.jobis.domain.application.service;

import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.application.domain.Application;
import team.returm.jobis.domain.application.domain.enums.ApplicationStatus;
import team.returm.jobis.domain.application.domain.repository.ApplicationRepository;
import team.returm.jobis.domain.application.exception.ApplicationCannotDeleteException;
import team.returm.jobis.domain.application.exception.ApplicationNotFoundException;
import team.returm.jobis.domain.application.exception.InvalidStudentException;
import team.returm.jobis.domain.recruitment.domain.Recruitment;
import team.returm.jobis.domain.student.domain.Student;
import team.returm.jobis.domain.user.facade.UserFacade;
import team.returm.jobis.global.annotation.Service;

@RequiredArgsConstructor
@Service
public class DeleteApplicationService {

    private final ApplicationRepository applicationRepository;
    private final UserFacade userFacade;


    public void execute(Long applicationId) {
        Student student = userFacade.getCurrentStudent();
        Application application = applicationRepository.queryApplicationById(applicationId)
                .orElseThrow(() -> ApplicationNotFoundException.EXCEPTION);
        Recruitment recruitment = application.getRecruitment();

        if (!application.getStudent().equals(student)) {
            throw InvalidStudentException.EXCEPTION;
        }

        if (!application.getApplicationStatus().equals(ApplicationStatus.REQUESTED)) {
            throw ApplicationCannotDeleteException.EXCEPTION;
        }

        applicationRepository.deleteApplication(application);
        recruitment.subApplicationRequestedCount();
    }
}
