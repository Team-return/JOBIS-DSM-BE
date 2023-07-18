package team.retum.jobis.domain.application.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.retum.jobis.domain.application.persistence.ApplicationAttachment;

public interface ApplicationAttachmentJpaRepository extends JpaRepository<ApplicationAttachment, String> {
}
