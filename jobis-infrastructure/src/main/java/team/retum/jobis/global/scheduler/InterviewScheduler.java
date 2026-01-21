package team.retum.jobis.global.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import team.retum.jobis.domain.interview.usecase.SendAfterInterviewNotificationUseCase;
import team.retum.jobis.domain.interview.usecase.SendTodayInterviewNotificationUseCase;
import team.retum.jobis.domain.interview.usecase.SendUpcomingInterviewNotificationUseCase;

@RequiredArgsConstructor
@Component
public class InterviewScheduler {

    private final SendTodayInterviewNotificationUseCase sendTodayInterviewNotificationUseCase;
    private final SendUpcomingInterviewNotificationUseCase sendUpcomingInterviewNotificationUseCase;
    private final SendAfterInterviewNotificationUseCase sendAfterInterviewNotificationUseCase;

    /**
     * 매일 오전 7시에 당일 면접 알림 전송
     */
    @Scheduled(cron = "0 0 7 * * *", zone = "Asia/Seoul")
    public void sendTodayInterviewNotifications() {
        sendTodayInterviewNotificationUseCase.execute();
    }

    /**
     * 매일 오전 9시에 1일 후/3일 후 면접 알림 전송
     */
    @Scheduled(cron = "0 0 9 * * *", zone = "Asia/Seoul")
    public void sendUpcomingInterviewNotifications() {
        sendUpcomingInterviewNotificationUseCase.execute();
    }

    /**
     * 매일 오후 6시에 면접 후 알림 전송
     * - 당일 면접을 본 학생들에게 전송
     */
    @Scheduled(cron = "0 0 18 * * *", zone = "Asia/Seoul")
    public void sendAfterInterviewNotifications() {
        sendAfterInterviewNotificationUseCase.execute();
    }
}
