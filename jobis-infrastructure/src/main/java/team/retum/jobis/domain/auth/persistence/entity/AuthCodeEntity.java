package team.retum.jobis.domain.auth.persistence.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;
import team.retum.jobis.domain.auth.exception.BadAuthCodeException;
import team.retum.jobis.domain.auth.exception.UnverifiedEmailException;

@Getter
@Builder
@RedisHash
public class AuthCodeEntity {

    @Id
    private String email;

    @Indexed
    private final String code;

    @Indexed
    private boolean isVerified;

    @TimeToLive
    private final Integer ttl;

    public AuthCodeEntity verify() {
        this.isVerified = true;
        return this;
    }

    public void checkIsVerified() {
        if (!this.isVerified) {
            throw UnverifiedEmailException.EXCEPTION;
        }
    }

    public void verifyCode(String code) {
        if (!this.code.equals(code)) {
            throw BadAuthCodeException.EXCEPTION;
        }
    }
}
