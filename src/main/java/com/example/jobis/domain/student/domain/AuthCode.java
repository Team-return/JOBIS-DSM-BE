package com.example.jobis.domain.student.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Getter
@Builder
@RedisHash
public class AuthCode {

    @Id
    private final String email;

    private String code;

    private boolean isVerified;

    @TimeToLive
    private Long ttl;

    public void updateAuthCode(String code, Long ttl) {
        this.code = code;
        this.ttl = ttl;
        this.isVerified = false;
     }

    public void verify() {
        this.isVerified = true;
    }
}
