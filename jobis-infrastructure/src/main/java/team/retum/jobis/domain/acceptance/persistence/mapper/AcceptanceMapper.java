package team.retum.jobis.domain.acceptance.persistence.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.acceptance.model.Acceptance;
import team.retum.jobis.domain.acceptance.persistence.entity.AcceptanceEntity;
import team.retum.jobis.domain.application.exception.ApplicationNotFoundException;
import team.retum.jobis.domain.company.exception.CompanyNotFoundException;
import team.retum.jobis.domain.company.persistence.entity.CompanyEntity;
import team.retum.jobis.domain.company.persistence.repository.CompanyJpaRepository;
import team.retum.jobis.domain.student.exception.StudentNotFoundException;
import team.retum.jobis.domain.student.persistence.entity.StudentEntity;
import team.retum.jobis.domain.student.persistence.repository.StudentJpaRepository;

@RequiredArgsConstructor
@Component
public class AcceptanceMapper {

    private final CompanyJpaRepository companyJpaRepository;
    private final StudentJpaRepository studentJpaRepository;

    public AcceptanceEntity toEntity(Acceptance domain) {
        CompanyEntity company = companyJpaRepository.findById(domain.getCompanyId())
                .orElseThrow(() -> CompanyNotFoundException.EXCEPTION);
        StudentEntity student = studentJpaRepository.findById(domain.getStudentId())
                .orElseThrow(() -> StudentNotFoundException.EXCEPTION);

        return AcceptanceEntity.builder()
                .id(domain.getId())
                .companyEntity(company)
                .contractDate(domain.getContractDate())
                .businessArea(domain.getBusinessArea())
                .year(domain.getYear())
                .tech(domain.getTech())
                .student(student)
                .build();
    }

    public Acceptance toDomain(AcceptanceEntity entity) {
        return Acceptance.builder()
                .contractDate(entity.getContractDate())
                .businessArea(entity.getBusinessArea())
                .tech(entity.getTech())
                .id(entity.getId())
                .companyId(entity.getCompany().getId())
                .studentId(entity.getStudent().getId())
                .build();
    }
}
