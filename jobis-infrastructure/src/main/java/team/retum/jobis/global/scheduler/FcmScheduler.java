package team.retum.jobis.global.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.notification.usecase.subscribe.SubscribeAllUserUseCase;

@Component
@RequiredArgsConstructor
public class FcmScheduler {

    private final SubscribeAllUserUseCase subscribeAllUserUseCase;

    /**
     * 매 4월 2일 9시 15분에 실행
     * deviceToken을 가지고 있는 유저들의 모든 토픽구독 상태를 true로 변경
     */
    @Scheduled(cron = "0 15 9 2 4 *", zone = "Asia/Seoul")
    public void subscribeAllUser() {
        subscribeAllUserUseCase.execute();
    }
}
