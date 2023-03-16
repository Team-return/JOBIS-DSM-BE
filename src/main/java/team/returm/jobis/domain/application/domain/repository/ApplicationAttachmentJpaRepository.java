package team.returm.jobis.domain.application.domain.repository;

import team.returm.jobis.domain.application.domain.ApplicationAttachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationAttachmentJpaRepository extends JpaRepository<ApplicationAttachment, String> {
}
