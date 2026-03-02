package team.retum.jobis.event.company;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.company.event.RecentCompanyEvent;

import java.time.Duration;

@RequiredArgsConstructor
@Component
public class RecentCompanyEventHandler {

    private final RedisTemplate<String, Long> redisTemplate;

    @Async("asyncTaskExecutor")
    @EventListener
    public void onRecentCompany(RecentCompanyEvent event) {

        String key = "recent:company:" + event.getUserId();
        long viewedAt = System.currentTimeMillis();

        redisTemplate.opsForZSet()
            .add(key, event.getCompanyId(), viewedAt);

        redisTemplate.expire(key, Duration.ofDays(30));
    }
}
