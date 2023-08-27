package team.retum.jobis.domain.user.persistence.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.user.model.User;
import team.retum.jobis.domain.user.persistence.entity.UserEntity;

@RequiredArgsConstructor
@Component
public class UserMapper {

    public UserEntity toEntity(User domain) {
        return UserEntity.builder()
                .id(domain.getId())
                .authority(domain.getAuthority())
                .accountId(domain.getAccountId())
                .password(domain.getPassword())
                .build();
    }

    public User toDomain(UserEntity entity) {
        return User.builder()
                .id(entity.getId())
                .authority(entity.getAuthority())
                .accountId(entity.getAccountId())
                .password(entity.getPassword())
                .build();
    }
}
