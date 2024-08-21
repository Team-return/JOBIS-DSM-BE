package team.retum.jobis.global.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.recruitment.usecase.ApplyRecruitmentUseCase;

@Component
@RequiredArgsConstructor
public class InterestedRecruitmentScheduler {

    private final ApplyRecruitmentUseCase applyRecruitmentUseCase;

    @Scheduled(cron = "0 20 8 * * *", zone = "Asia/Seoul")
    public void registerInterestedRecruitment() {
        applyRecruitmentUseCase.executeInterestCodeMatch();
    }
}
