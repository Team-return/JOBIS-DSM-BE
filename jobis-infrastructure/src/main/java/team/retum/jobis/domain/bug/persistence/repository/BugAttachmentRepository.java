package team.retum.jobis.domain.bug.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.retum.jobis.domain.bug.persistence.BugAttachment;
import team.retum.jobis.domain.bug.persistence.BugAttachmentId;

public interface BugAttachmentRepository extends JpaRepository<BugAttachment, BugAttachmentId> {
}
