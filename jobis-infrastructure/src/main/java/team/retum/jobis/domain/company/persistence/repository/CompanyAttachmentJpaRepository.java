package team.retum.jobis.domain.company.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.retum.jobis.domain.company.persistence.CompanyAttachment;

public interface CompanyAttachmentJpaRepository extends JpaRepository<CompanyAttachment, Long> {
}
