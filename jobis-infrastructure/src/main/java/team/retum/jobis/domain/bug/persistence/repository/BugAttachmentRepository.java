package team.retum.jobis.domain.bug.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.retum.jobis.domain.bug.persistence.BugAttachmentEntity;
import team.retum.jobis.domain.bug.persistence.BugAttachmentId;

public interface BugAttachmentRepository extends JpaRepository<BugAttachmentEntity, BugAttachmentId> {
}
