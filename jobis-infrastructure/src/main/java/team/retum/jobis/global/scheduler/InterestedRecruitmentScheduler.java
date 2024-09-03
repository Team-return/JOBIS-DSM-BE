package team.retum.jobis.global.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.recruitment.usecase.ApplyRecruitmentUseCase;

@Component
@RequiredArgsConstructor
public class InterestedRecruitmentScheduler {

    private final ApplyRecruitmentUseCase applyRecruitmentUseCase;

    /**
     * 관심 분야에 추가한 모집의뢰서 들어왔을때 알림
     */
    @Scheduled(cron = "0 20 8 * * *", zone = "Asia/Seoul")
    public void registerInterestedRecruitment() {
        applyRecruitmentUseCase.executeInterestCodeMatch();
    }
}
