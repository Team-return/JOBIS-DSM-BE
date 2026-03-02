package team.retum.jobis.global.config.cache;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import team.retum.jobis.common.spi.CachePort;

import java.util.Set;

import static team.retum.jobis.global.config.cache.CacheName.*;

@Component
@RequiredArgsConstructor
public class CacheAdapter implements CachePort {

    private final RedisTemplate<String, Long> redisTemplate;

    @Override
    public Set<Long> getRecentCompanyId(Long studentId) {
        String key = RECENT_COMPANY + studentId;

        return redisTemplate.opsForZSet()
            .reverseRange(key,0,-1);
    }
}
