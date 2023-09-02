package team.retum.jobis.domain.bug.persistence.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.bug.model.BugAttachment;
import team.retum.jobis.domain.bug.model.BugReport;
import team.retum.jobis.domain.bug.persistence.entity.BugReportEntity;
import team.retum.jobis.domain.student.persistence.entity.StudentEntity;
import team.retum.jobis.domain.student.persistence.repository.StudentJpaRepository;

@RequiredArgsConstructor
@Component
public class BugReportMapper {

    private final StudentJpaRepository studentJpaRepository;

    public BugReportEntity toEntity(BugReport domain) {
        StudentEntity student = studentJpaRepository.findById(domain.getStudentId())
                .orElse(null);
        return BugReportEntity.builder()
                .id(domain.getId())
                .content(domain.getContent())
                .title(domain.getTitle())
                .developmentArea(domain.getDevelopmentArea())
                .student(student)
                .build();
    }

    public BugReport toDomain(BugReportEntity entity) {
        return BugReport.builder()
                .id(entity.getId())
                .content(entity.getContent())
                .title(entity.getTitle())
                .developmentArea(entity.getDevelopmentArea())
                .studentId(entity.getStudent().getId())
                .bugAttachments(
                        entity.getBugAttachments().stream()
                                .map(
                                        bugAttachmentEntity -> BugAttachment.builder()
                                                .bugReportId(bugAttachmentEntity.getBugReport().getId())
                                                .attachmentUrl(bugAttachmentEntity.getAttachmentUrl())
                                                .build()
                                ).toList()
                )
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
