package team.retum.jobis.domain.acceptance.persistence.mapper;

import team.retum.jobis.domain.acceptance.model.Acceptance;
import team.retum.jobis.domain.company.exception.CompanyNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.acceptance.persistence.entity.AcceptanceEntity;
import team.retum.jobis.domain.company.persistence.entity.CompanyEntity;
import team.retum.jobis.domain.company.persistence.repository.CompanyJpaRepository;

@RequiredArgsConstructor
@Component
public class AcceptanceMapper {

    private final CompanyJpaRepository companyJpaRepository;

    public AcceptanceEntity toEntity(Acceptance domain) {
        CompanyEntity company = companyJpaRepository.findById(domain.getCompanyId())
                .orElseThrow(() -> CompanyNotFoundException.EXCEPTION);

        return AcceptanceEntity.builder()
                .id(domain.getId())
                .companyEntity(company)
                .contractDate(domain.getContractDate())
                .businessArea(domain.getBusinessArea())
                .year(domain.getYear())
                .studentName(domain.getStudentName())
                .studentGcn(domain.getStudentGcn())
                .tech(domain.getTech())
                .build();
    }

    public Acceptance toDomain(AcceptanceEntity entity) {
        return Acceptance.builder()
                .contractDate(entity.getContractDate())
                .businessArea(entity.getBusinessArea())
                .tech(entity.getTech())
                .studentName(entity.getStudentName())
                .studentGcn(entity.getStudentGcn())
                .id(entity.getId())
                .companyId(entity.getCompany().getId())
                .build();
    }
}
