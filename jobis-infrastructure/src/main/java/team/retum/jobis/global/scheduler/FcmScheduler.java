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
     * 현재 학생들의 모든 토픽구독 상태를 true로 변경
     */
    @Scheduled(cron = "0 10 9 2 4 *", zone = "Asia/Seoul") //매 4월 2일 9시 0분에 실행
    public void subscribeAllUser() {
        subscribeAllUserUseCase.execute();
    }
}
