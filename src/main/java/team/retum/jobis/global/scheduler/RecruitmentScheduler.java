package team.retum.jobis.global.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.recruitment.service.ChangeRecruitmentStatusSchedulerService;

@Component
@RequiredArgsConstructor
public class RecruitmentScheduler {

    private final ChangeRecruitmentStatusSchedulerService changeRecruitmentService;

    @Scheduled(cron = "0 0 0 * * *", zone = "Asia/Seoul")
    public void changeRecruitmentStatus() {
        changeRecruitmentService.execute();
    }
}
