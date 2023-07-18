package team.retum.jobis.domain.application.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.application.persistence.entity.ApplicationEntity;
import team.retum.jobis.domain.application.persistence.repository.ApplicationRepository;
import com.example.jobisapplication.domain.application.exception.ApplicationNotFoundException;
import team.retum.jobis.domain.student.persistence.entity.StudentEntity;
import team.retum.jobis.domain.user.facade.UserFacade;
import com.example.jobisapplication.common.annotation.Service;

@RequiredArgsConstructor
@Service
public class DeleteApplicationService {

    private final ApplicationRepository applicationRepository;
    private final UserFacade userFacade;

    public void execute(Long applicationId) {
        StudentEntity studentEntity = userFacade.getCurrentStudent();

        ApplicationEntity applicationEntity = applicationRepository.queryApplicationById(applicationId)
                .orElseThrow(() -> ApplicationNotFoundException.EXCEPTION);
        applicationEntity.checkIsDeleteable(studentEntity);

        applicationRepository.deleteApplication(applicationEntity);
    }
}
