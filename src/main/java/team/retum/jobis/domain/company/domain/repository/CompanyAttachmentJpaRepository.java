package team.retum.jobis.domain.company.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.retum.jobis.domain.company.domain.CompanyAttachment;

public interface CompanyAttachmentJpaRepository extends JpaRepository<CompanyAttachment, Long> {
}
