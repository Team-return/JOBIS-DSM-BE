package com.example.jobis.domain.auth.domain;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;



@Getter
@RedisHash
@Builder
public class RefreshToken {

    @Id
    private String id;

    private String token;

    @TimeToLive
    private Long ttl;
}
