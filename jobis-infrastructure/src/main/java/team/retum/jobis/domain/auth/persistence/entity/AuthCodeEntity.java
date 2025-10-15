package team.retum.jobis.domain.auth.persistence.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@Builder
@RedisHash
public class AuthCodeEntity {

    @Indexed
    private final String code;

    @TimeToLive
    private final Integer ttl;

    @Id
    private String email;

    @Indexed
    private boolean isVerified;
}
