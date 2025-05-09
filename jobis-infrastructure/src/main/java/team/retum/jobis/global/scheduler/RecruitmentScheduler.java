package team.retum.jobis.global.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.recruitment.usecase.ChangeRecruitmentStatusSchedulerUseCase;

@Component
@RequiredArgsConstructor
public class RecruitmentScheduler {

    private final ChangeRecruitmentStatusSchedulerUseCase changeRecruitmentService;

    /**
     * 매일 밤 12시 마다 모집의뢰서의 상태를 확인 후 상태를 변경
     */
    @Scheduled(cron = "* * * * * *", zone = "Asia/Seoul")
    public void changeRecruitmentStatus() {
        changeRecruitmentService.execute();
    }
}
