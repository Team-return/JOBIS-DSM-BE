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

    private final RedisTemplate<String, String> redisTemplate;

    @Async("asyncTaskExecutor")
    @EventListener
    public void onRecentCompany(RecentCompanyEvent event) {
        String key = CacheName.RECENT_COMPANY + event.getUserId();
        long viewedAt = System.currentTimeMillis();

        redisTemplate.opsForZSet()
            .add(key, String.valueOf(event.getCompanyId()), viewedAt);

        redisTemplate.expire(key, Duration.ofDays(30));
    }
}
