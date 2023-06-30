package team.retum.jobis.domain.application.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.application.domain.Application;
import team.retum.jobis.domain.application.domain.ApplicationAttachment;
import team.retum.jobis.domain.application.domain.enums.ApplicationStatus;
import team.retum.jobis.domain.application.domain.repository.ApplicationRepository;
import team.retum.jobis.domain.application.exception.ApplicationAlreadyExistsException;
import team.retum.jobis.domain.application.presentation.dto.request.CreateApplicationRequest;
import team.retum.jobis.domain.recruitment.domain.Recruitment;
import team.retum.jobis.domain.recruitment.domain.repository.RecruitmentRepository;
import team.retum.jobis.domain.recruitment.exception.RecruitmentNotFoundException;
import team.retum.jobis.domain.student.domain.Student;
import team.retum.jobis.domain.user.facade.UserFacade;
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
