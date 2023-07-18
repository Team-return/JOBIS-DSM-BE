package team.retum.jobis.domain.application.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.application.persistence.Application;
import team.retum.jobis.domain.application.persistence.ApplicationAttachment;
import team.retum.jobis.domain.application.persistence.enums.ApplicationStatus;
import team.retum.jobis.domain.application.persistence.repository.ApplicationRepository;
import team.retum.jobis.domain.application.exception.ApplicationAlreadyExistsException;
import team.retum.jobis.domain.application.presentation.dto.request.CreateApplicationRequest;
import team.retum.jobis.domain.recruitment.persistence.Recruitment;
import team.retum.jobis.domain.recruitment.persistence.repository.RecruitmentRepository;
import team.retum.jobis.domain.recruitment.exception.RecruitmentNotFoundException;
import team.retum.jobis.domain.student.persistence.Student;
import team.retum.jobis.domain.persistence.facade.UserFacade;
import team.retum.jobis.global.annotation.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CreateApplicationService {

    private final ApplicationRepository applicationRepository;
    private final UserFacade userFacade;
    private final RecruitmentRepository recruitmentRepository;

    public void execute(CreateApplicationRequest request, Long recruitmentId) {
        Student student = userFacade.getCurrentStudent();
        student.checkIs3rdGrade();

        Recruitment recruitment = recruitmentRepository.queryRecruitmentById(recruitmentId)
                .orElseThrow(() -> RecruitmentNotFoundException.EXCEPTION);
        recruitment.checkIsApplicatable();

        if (applicationRepository.existsApplicationByStudentIdAndApplicationStatusIn(
                student.getId(),
                List.of(
                        ApplicationStatus.APPROVED,
                        ApplicationStatus.FIELD_TRAIN,
                        ApplicationStatus.PASS
                )
        )) {
            throw ApplicationAlreadyExistsException.EXCEPTION;
        }

        Application application = applicationRepository.saveApplication(
                Application.builder()
                        .student(student)
                        .recruitment(recruitment)
                        .applicationStatus(ApplicationStatus.REQUESTED)
                        .build()
        );

        List<ApplicationAttachment> applicationAttachmentList = request.getAttachments()
                .stream()
                .map(attachment -> new ApplicationAttachment(
                        attachment.getUrl(),
                        attachment.getType(),
                        application
                )).toList();

        applicationRepository.saveAllApplicationAttachment(applicationAttachmentList);
    }
}
