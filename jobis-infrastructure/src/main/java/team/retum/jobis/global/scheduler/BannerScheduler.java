package team.retum.jobis.global.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.banner.usecase.UpdateBannerStatusUseCase;

@Component
@RequiredArgsConstructor
public class BannerScheduler {

    private final UpdateBannerStatusUseCase updateBannerStatusUseCase;

    @Scheduled(cron = "0 0 0 * * *", zone = "Asia/Seoul")
    public void updateBannerStatus() {
        updateBannerStatusUseCase.execute();
    }
}
