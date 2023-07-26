package team.retum.jobis.global.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.example.jobisapplication.domain.recruitment.usecase.ChangeRecruitmentStatusSchedulerUseCase;

@Component
@RequiredArgsConstructor
public class RecruitmentScheduler {

    private final ChangeRecruitmentStatusSchedulerUseCase changeRecruitmentService;

    @Scheduled(cron = "0 0 0 * * *", zone = "Asia/Seoul")
    public void changeRecruitmentStatus() {
        changeRecruitmentService.execute();
    }
}
