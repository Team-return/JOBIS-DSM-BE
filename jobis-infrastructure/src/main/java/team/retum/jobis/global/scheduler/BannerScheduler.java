package team.retum.jobis.global.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.banner.usecase.UpdateBannerStatusUseCase;

@Component
@RequiredArgsConstructor
public class BannerScheduler {

    private final UpdateBannerStatusUseCase updateBannerStatusUseCase;

    /**
     * 매일 배너의 상태를 확인 후 기간이 지난 배너를 삭제
     */
    @Scheduled(cron = "0 0 0 * * *", zone = "Asia/Seoul")
    public void updateBannerStatus() {
        updateBannerStatusUseCase.execute();
    }
}
