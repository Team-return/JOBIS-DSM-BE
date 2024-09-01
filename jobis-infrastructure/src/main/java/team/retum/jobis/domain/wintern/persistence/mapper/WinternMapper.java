package team.retum.jobis.domain.wintern.persistence.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.wintern.model.Wintern;
import team.retum.jobis.domain.wintern.persistence.entity.WinternEntity;

@RequiredArgsConstructor
@Component
public class WinternMapper {

    public WinternEntity toEntity(Wintern wintern) {
        return new WinternEntity(wintern.isWinterInterned());
    }

    public Wintern toDomain(WinternEntity entity) {
        return Wintern.builder()
            .isWinterInterned(entity.isWinterIntern())
            .build();
    }
}
