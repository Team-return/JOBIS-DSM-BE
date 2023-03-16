package team.returm.jobis.domain.auth.domain;

import team.returm.jobis.domain.user.domain.enums.Authority;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import java.util.UUID;


@Getter
@RedisHash
@Builder
public class RefreshToken {

    @Id
    private UUID id;

    @Indexed
    private String token;

    private Authority authority;

    @TimeToLive
    private Long ttl;

    public void update(String token, Long ttl) {
        this.token = token;
        this.ttl = ttl;
    }
}
