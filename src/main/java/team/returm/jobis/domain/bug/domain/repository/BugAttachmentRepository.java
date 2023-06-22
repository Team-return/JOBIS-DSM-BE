package team.returm.jobis.domain.bug.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.returm.jobis.domain.bug.domain.BugAttachment;
import team.returm.jobis.domain.bug.domain.BugAttachmentId;

public interface BugAttachmentRepository extends JpaRepository<BugAttachment, BugAttachmentId> {
}
