package team.retum.jobis.domain.interest.persistence.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.interest.persistence.entity.InterestEntity;
import team.retum.jobis.domain.interest.model.Interest;

@RequiredArgsConstructor
@Component
public class InterestMapper {

    public InterestEntity toEntity(Interest domain) {
        return InterestEntity.builder()
            .id(domain.getId())
            .studentId(domain.getStudentId())
            .code(domain.getCode())
            .build();
    }

    public Interest toDomain(InterestEntity entity) {
        return Interest.builder()
            .id(entity.getId())
            .studentId(entity.getStudentId())
            .code(entity.getCode())
            .build();
    }
}
