package team.retum.jobis.domain.company.mapper;

import com.example.jobisapplication.domain.company.exception.CompanyNotFoundException;
import com.example.jobisapplication.domain.company.model.Company;
import com.example.jobisapplication.domain.company.model.CompanyAttachment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.company.persistence.entity.CompanyAttachmentEntity;
import team.retum.jobis.domain.company.persistence.entity.CompanyEntity;
import team.retum.jobis.domain.company.persistence.repository.CompanyAttachmentJpaRepository;
import team.retum.jobis.domain.company.persistence.repository.CompanyJpaRepository;

@RequiredArgsConstructor
@Component
public class CompanyAttachmentMapper {

    private final CompanyJpaRepository companyJpaRepository;

    public CompanyAttachmentEntity toEntity(CompanyAttachment domain) {
        CompanyEntity company = companyJpaRepository.findById(domain.getCompanyId())
                .orElseThrow(() -> CompanyNotFoundException.EXCEPTION);

        return new CompanyAttachmentEntity(domain.getAttachmentUrl(), company);
    }

    public CompanyAttachment toDomain(CompanyAttachmentEntity entity) {
        return CompanyAttachment.builder()
                .id(entity.getId())
                .companyId(entity.getCompanyEntity().getId())
                .attachmentUrl(entity.getAttachmentUrl())
                .build();
    }
}
