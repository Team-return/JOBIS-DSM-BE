package com.example.jobis.domain.auth.domain;

import com.example.jobis.domain.user.domain.enums.Authority;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;


@Getter
@RedisHash
@Builder
public class RefreshToken {

    @Id
    private String id;

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
