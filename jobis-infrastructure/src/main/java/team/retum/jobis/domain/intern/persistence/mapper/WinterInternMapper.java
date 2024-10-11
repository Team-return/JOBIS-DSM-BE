package team.retum.jobis.domain.intern.persistence.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.intern.model.WinterIntern;
import team.retum.jobis.domain.intern.persistence.entity.WinterInternEntity;

@RequiredArgsConstructor
@Component
public class WinterInternMapper {

    public WinterInternEntity toEntity(WinterIntern winterIntern) {
        return new WinterInternEntity(winterIntern.isWinterInterned());
    }

    public WinterIntern toDomain(WinterInternEntity entity) {
        return WinterIntern.builder()
            .isWinterInterned(entity.isWinterIntern())
            .build();
    }
}
