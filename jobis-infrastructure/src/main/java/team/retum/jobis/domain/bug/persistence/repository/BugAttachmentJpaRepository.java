package team.retum.jobis.domain.bug.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.retum.jobis.domain.bug.persistence.entity.BugAttachmentEntity;
import team.retum.jobis.domain.bug.persistence.entity.BugAttachmentId;

public interface BugAttachmentJpaRepository extends JpaRepository<BugAttachmentEntity, BugAttachmentId> {
}
