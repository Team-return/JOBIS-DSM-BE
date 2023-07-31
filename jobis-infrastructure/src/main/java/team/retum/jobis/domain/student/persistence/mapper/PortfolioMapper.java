package team.retum.jobis.domain.student.persistence.mapper;

import team.retum.jobis.domain.student.exception.StudentNotFoundException;
import team.retum.jobis.domain.student.model.Portfolio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.student.persistence.entity.PortfolioEntity;
import team.retum.jobis.domain.student.persistence.entity.StudentEntity;
import team.retum.jobis.domain.student.persistence.repository.StudentJpaRepository;

@RequiredArgsConstructor
@Component
public class PortfolioMapper {

    private final StudentJpaRepository studentJpaRepository;

    public PortfolioEntity toEntity(Portfolio domain) {
        StudentEntity student = studentJpaRepository.findById(domain.getStudentId())
                .orElseThrow(() -> StudentNotFoundException.EXCEPTION);

        return PortfolioEntity.builder()
                .studentEntity(student)
                .portfolioUrl(domain.getPortfolioUrl())
                .build();
    }

    public Portfolio toDomain(PortfolioEntity entity) {
        return Portfolio.builder()
                .id(entity.getId())
                .portfolioUrl(entity.getPortfolioUrl())
                .studentId(entity.getStudent().getId())
                .build();
    }
}
