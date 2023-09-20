package team.retum.jobis.domain.bug.persistence.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.bug.model.BugAttachment;
import team.retum.jobis.domain.bug.model.BugReport;
import team.retum.jobis.domain.bug.persistence.entity.BugAttachmentEntity;
import team.retum.jobis.domain.bug.persistence.entity.BugReportEntity;
import team.retum.jobis.domain.student.exception.StudentNotFoundException;
import team.retum.jobis.domain.student.persistence.entity.StudentEntity;
import team.retum.jobis.domain.student.persistence.repository.StudentJpaRepository;

import java.util.List;

@RequiredArgsConstructor
@Component
public class BugReportMapper {

    private final StudentJpaRepository studentJpaRepository;

    public BugReportEntity toEntity(BugReport domain) {
        StudentEntity student = studentJpaRepository.findById(domain.getStudentId())
                .orElseThrow(() -> StudentNotFoundException.EXCEPTION);

        List<BugAttachmentEntity> attachments = domain.getAttachments().stream()
                .map(attachment -> new BugAttachmentEntity(attachment.getAttachmentUrl()))
                .toList();

        return BugReportEntity.builder()
                .id(domain.getId())
                .content(domain.getContent())
                .title(domain.getTitle())
                .developmentArea(domain.getDevelopmentArea())
                .student(student)
                .attachments(attachments)
                .build();
    }

    public BugReport toDomain(BugReportEntity entity) {
        List<BugAttachment> attachments = entity.getAttachments().stream()
                .map(attachment -> new BugAttachment(attachment.getAttachmentUrl()))
                .toList();

        return BugReport.builder()
                .id(entity.getId())
                .content(entity.getContent())
                .title(entity.getTitle())
                .developmentArea(entity.getDevelopmentArea())
                .studentId(entity.getStudent().getId())
                .attachments(attachments)
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
