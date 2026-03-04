package team.retum.jobis.event.company;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.company.event.RecentCompanyEvent;
import team.retum.jobis.global.config.cache.CacheName;

import java.time.Duration;

@RequiredArgsConstructor
@Component
public class RecentCompanyEventHandler {

    private static final long MAX_RECENT_COMPANY_SIZE = 50L;
    private final RedisTemplate<String, String> redisTemplate;

    @Async("asyncTaskExecutor")
    @EventListener
    public void onRecentCompany(RecentCompanyEvent event) {

        String key = CacheName.RECENT_COMPANY + event.getUserId();
        long viewedAt = System.currentTimeMillis();

        redisTemplate.opsForZSet()
            .add(key, String.valueOf(event.getCompanyId()), viewedAt);

        Long size = redisTemplate.opsForZSet().zCard(key);
        if (size != null && size > MAX_RECENT_COMPANY_SIZE) {
            redisTemplate.opsForZSet().removeRange(key, 0, size - MAX_RECENT_COMPANY_SIZE - 1);
        }
        redisTemplate.expire(key, Duration.ofDays(30));
    }
}
