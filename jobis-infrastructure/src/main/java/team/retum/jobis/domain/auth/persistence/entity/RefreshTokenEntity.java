package team.retum.jobis.domain.auth.persistence.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;
import team.retum.jobis.domain.auth.model.Authority;
import team.retum.jobis.domain.auth.model.PlatformType;


@Getter
@RedisHash
@Builder
public class RefreshTokenEntity {

    @Id
    @Indexed
    private String token;

    @Indexed
    private Long userId;

    private Authority authority;

    @Indexed
    private PlatformType platformType;

    @TimeToLive
    private Long ttl;

}
