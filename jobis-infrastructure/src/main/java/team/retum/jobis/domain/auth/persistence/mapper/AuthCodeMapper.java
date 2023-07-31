package team.retum.jobis.domain.auth.persistence.mapper;

import team.retum.jobis.domain.auth.model.AuthCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.auth.persistence.entity.AuthCodeEntity;

@RequiredArgsConstructor
@Component
public class AuthCodeMapper {

    public AuthCodeEntity toEntity(AuthCode domain) {
        return AuthCodeEntity.builder()
                .code(domain.getCode())
                .email(domain.getEmail())
                .isVerified(domain.isVerified())
                .ttl(domain.getTtl())
                .build();
    }

    public AuthCode toDomain(AuthCodeEntity entity) {
        return AuthCode.builder()
                .code(entity.getCode())
                .email(entity.getEmail())
                .isVerified(entity.isVerified())
                .ttl(entity.getTtl())
                .build();
    }
}
