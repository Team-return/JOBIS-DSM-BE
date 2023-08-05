package team.retum.jobis.domain.auth.persistence.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;
import team.retum.jobis.domain.auth.model.Authority;


@Getter
@RedisHash
@Builder
public class RefreshTokenEntity {

    @Id
    private Long id;

    @Indexed
    private String token;

    private Authority authority;

    @TimeToLive
    private Long ttl;

}
