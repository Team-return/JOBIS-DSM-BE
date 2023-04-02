package team.returm.jobis.domain.application.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.returm.jobis.domain.application.domain.ApplicationAttachment;

public interface ApplicationAttachmentJpaRepository extends JpaRepository<ApplicationAttachment, String> {
}
