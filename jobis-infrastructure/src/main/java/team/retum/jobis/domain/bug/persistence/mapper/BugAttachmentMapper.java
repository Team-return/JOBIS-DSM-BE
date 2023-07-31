package team.retum.jobis.domain.bug.persistence.mapper;

import team.retum.jobis.domain.bug.model.BugAttachment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.bug.persistence.entity.BugAttachmentEntity;
import team.retum.jobis.domain.bug.persistence.entity.BugReportEntity;
import team.retum.jobis.domain.bug.persistence.repository.BugReportJpaRepository;

@RequiredArgsConstructor
@Component
public class BugAttachmentMapper {

    private final BugReportJpaRepository bugReportJpaRepository;

    public BugAttachment toDomain(BugAttachmentEntity entity) {
        return BugAttachment.builder()
                .bugReportId(entity.getBugReport().getId())
                .attachmentUrl(entity.getAttachmentUrl())
                .build();
    }

    public BugAttachmentEntity toEntity(BugAttachment domain) {
        BugReportEntity bugReportEntity = bugReportJpaRepository.findById(domain.getBugReportId())
                .orElse(null);
        return new BugAttachmentEntity(bugReportEntity, domain.getAttachmentUrl());
    }
}
