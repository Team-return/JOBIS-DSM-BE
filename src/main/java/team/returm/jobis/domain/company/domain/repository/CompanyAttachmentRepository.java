package team.returm.jobis.domain.company.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team.returm.jobis.domain.company.domain.CompanyAttachment;

public interface CompanyAttachmentRepository extends JpaRepository<CompanyAttachment, Long> {
}
