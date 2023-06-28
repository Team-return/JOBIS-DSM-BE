package team.retum.jobis.domain.bug.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.retum.jobis.domain.bug.domain.BugAttachment;
import team.retum.jobis.domain.bug.domain.BugAttachmentId;

public interface BugAttachmentRepository extends JpaRepository<BugAttachment, BugAttachmentId> {
}
