package team.retum.jobis.global.config.cache;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import team.retum.jobis.common.spi.CachePort;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static team.retum.jobis.global.config.cache.CacheName.*;

@Component
@RequiredArgsConstructor
public class CacheAdapter implements CachePort {

    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public List<Long> getRecentCompanyId(Long studentId) {
        String key = RECENT_COMPANY + studentId;

        Set<String> result = redisTemplate.opsForZSet()
            .reverseRange(key,0,-1);

        return result == null ? List.of() : result.stream()
            .map(Long::parseLong).collect(Collectors.toList());
    }
}
