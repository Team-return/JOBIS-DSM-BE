package team.retum.jobis.domain.application.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.application.persistence.entity.ApplicationEntity;
import team.retum.jobis.domain.application.persistence.entity.ApplicationAttachmentEntity;
import com.example.jobisapplication.domain.application.model.ApplicationStatus;
import team.retum.jobis.domain.application.persistence.repository.ApplicationRepository;
import com.example.jobisapplication.domain.application.exception.ApplicationAlreadyExistsException;
import team.retum.jobis.domain.application.presentation.dto.request.CreateApplicationWebRequest;
import team.retum.jobis.domain.recruitment.persistence.entity.RecruitmentEntity;
import team.retum.jobis.domain.recruitment.persistence.repository.RecruitmentRepository;
import com.example.jobisapplication.domain.recruitment.exception.RecruitmentNotFoundException;
import team.retum.jobis.domain.student.persistence.entity.StudentEntity;
import team.retum.jobis.domain.user.facade.UserFacade;
import com.example.jobisapplication.common.annotation.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CreateApplicationService {

    private final ApplicationRepository applicationRepository;
    private final UserFacade userFacade;
    private final RecruitmentRepository recruitmentRepository;

    public void execute(CreateApplicationWebRequest request, Long recruitmentId) {
        StudentEntity studentEntity = userFacade.getCurrentStudent();
        studentEntity.checkIs3rdGrade();

        RecruitmentEntity recruitmentEntity = recruitmentRepository.queryRecruitmentById(recruitmentId)
                .orElseThrow(() -> RecruitmentNotFoundException.EXCEPTION);
        recruitmentEntity.checkIsApplicatable();

        if (applicationRepository.existsApplicationByStudentIdAndApplicationStatusIn(
                studentEntity.getId(),
                List.of(
                        ApplicationStatus.APPROVED,
                        ApplicationStatus.FIELD_TRAIN,
                        ApplicationStatus.PASS
                )
        )) {
            throw ApplicationAlreadyExistsException.EXCEPTION;
        }

        ApplicationEntity applicationEntity = applicationRepository.saveApplication(
                ApplicationEntity.builder()
                        .student(studentEntity)
                        .recruitment(recruitmentEntity)
                        .applicationStatus(ApplicationStatus.REQUESTED)
                        .build()
        );

        List<ApplicationAttachmentEntity> applicationAttachmentEntityList = request.getAttachments()
                .stream()
                .map(attachment -> new ApplicationAttachmentEntity(
                        attachment.getUrl(),
                        attachment.getType(),
                        applicationEntity
                )).toList();

        applicationRepository.saveAllApplicationAttachment(applicationAttachmentEntityList);
    }
}
