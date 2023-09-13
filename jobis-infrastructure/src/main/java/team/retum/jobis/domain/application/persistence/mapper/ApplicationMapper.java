package team.retum.jobis.domain.application.persistence.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.application.model.Application;
import team.retum.jobis.domain.application.model.ApplicationAttachment;
import team.retum.jobis.domain.application.persistence.entity.ApplicationAttachmentEntity;
import team.retum.jobis.domain.application.persistence.entity.ApplicationEntity;
import team.retum.jobis.domain.recruitment.exception.RecruitmentNotFoundException;
import team.retum.jobis.domain.recruitment.persistence.entity.RecruitmentEntity;
import team.retum.jobis.domain.recruitment.persistence.repository.RecruitmentJpaRepository;
import team.retum.jobis.domain.student.exception.StudentNotFoundException;
import team.retum.jobis.domain.student.persistence.entity.StudentEntity;
import team.retum.jobis.domain.student.persistence.repository.StudentJpaRepository;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ApplicationMapper {

    private final StudentJpaRepository studentJpaRepository;
    private final RecruitmentJpaRepository recruitmentJpaRepository;

    public ApplicationEntity toEntity(Application domain) {
        StudentEntity student = studentJpaRepository.findById(domain.getStudentId())
                .orElseThrow(() -> StudentNotFoundException.EXCEPTION);

        RecruitmentEntity recruitment = recruitmentJpaRepository.findById(domain.getRecruitmentId())
                .orElseThrow(() -> RecruitmentNotFoundException.EXCEPTION);

        List<ApplicationAttachmentEntity> attachments = domain.getAttachments().stream()
                .map(attachment -> new ApplicationAttachmentEntity(attachment.getAttachmentUrl(), attachment.getType()))
                .toList();
        System.out.println(attachments);

        return ApplicationEntity.builder()
                .id(domain.getId())
                .applicationStatus(domain.getApplicationStatus())
                .studentEntity(student)
                .recruitmentEntity(recruitment)
                .rejectionReason(domain.getRejectionReason())
                .startDate(domain.getStartDate())
                .endDate(domain.getEndDate())
                .attachments(attachments)
                .build();
    }

    public Application toDomain(ApplicationEntity entity) {
        List<ApplicationAttachment> attachments = entity.getAttachments().stream()
                .map(attachment -> new ApplicationAttachment(attachment.getAttachmentUrl(), attachment.getType()))
                .toList();

        return Application.builder()
                .id(entity.getId())
                .applicationStatus(entity.getApplicationStatus())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .rejectionReason(entity.getRejectionReason())
                .studentId(entity.getStudent().getId())
                .recruitmentId(entity.getRecruitment().getId())
                .attachments(attachments)
                .build();
    }
}
