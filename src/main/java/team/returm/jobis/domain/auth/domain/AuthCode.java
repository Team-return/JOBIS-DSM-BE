package team.returm.jobis.domain.auth.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

@Getter
@RedisHash
public class AuthCode {

    @Id
    private String email;

    @Indexed
    private final String code;

    private boolean isVerified;

    @TimeToLive
    private final Integer ttl;

    @Builder
    public AuthCode(String email, String code) {
        this.email = email;
        this.code = code;
        this.isVerified = false;
        this.ttl = 300;
    }

    public AuthCode verify() {
        this.isVerified = true;
        return this;
    }
}
