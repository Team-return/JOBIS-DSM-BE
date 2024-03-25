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
            .token(domain.getToken())
            .userId(domain.getUserId())
            .authority(domain.getAuthority())
            .platformType(domain.getPlatformType())
            .ttl(domain.getTtl())
            .build();
    }

    public RefreshToken toDomain(RefreshTokenEntity entity) {
        return RefreshToken.builder()
            .token(entity.getToken())
            .userId(entity.getUserId())
            .authority(entity.getAuthority())
            .platformType(entity.getPlatformType())
            .ttl(entity.getTtl())
            .build();
    }
}
