package team.retum.jobis.domain.application.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.application.persistence.Application;
import team.retum.jobis.domain.application.persistence.repository.ApplicationRepository;
import team.retum.jobis.domain.application.exception.ApplicationNotFoundException;
import team.retum.jobis.domain.student.persistence.Student;
import team.retum.jobis.domain.persistence.facade.UserFacade;
import team.retum.jobis.global.annotation.Service;

@RequiredArgsConstructor
@Service
public class DeleteApplicationService {

    private final ApplicationRepository applicationRepository;
    private final UserFacade userFacade;

    public void execute(Long applicationId) {
        Student student = userFacade.getCurrentStudent();

        Application application = applicationRepository.queryApplicationById(applicationId)
                .orElseThrow(() -> ApplicationNotFoundException.EXCEPTION);
        application.checkIsDeleteable(student);

        applicationRepository.deleteApplication(application);
    }
}
