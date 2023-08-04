package team.retum.jobis.domain.auth.persistence.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.auth.model.RefreshToken;
import team.retum.jobis.domain.auth.persistence.entity.RefreshTokenEntity;

@RequiredArgsConstructor
@Component
public class RefreshTokenMapper {

    public RefreshTokenEntity toEntity(RefreshToken domain) {
        return RefreshTokenEntity.builder()
                .id(domain.getId())
                .token(domain.getToken())
                .authority(domain.getAuthority())
                .ttl(domain.getTtl())
                .build();
    }

    public RefreshToken toDomain(RefreshTokenEntity entity) {
        return RefreshToken.builder()
                .id(entity.getId())
                .token(entity.getToken())
                .authority(entity.getAuthority())
                .ttl(entity.getTtl())
                .build();
    }
}
