package team.retum.jobis.event.recruitment;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import team.retum.jobis.domain.auth.model.Authority;
import team.retum.jobis.domain.bookmark.spi.QueryBookmarkPort;
import team.retum.jobis.domain.bookmark.spi.vo.BookmarkUserVO;
import team.retum.jobis.domain.notification.model.Notification;
import team.retum.jobis.domain.notification.model.Topic;
import team.retum.jobis.domain.notification.spi.CommandNotificationPort;
import team.retum.jobis.domain.recruitment.event.RecruitmentStatusChangedEvent;
import team.retum.jobis.domain.recruitment.model.Recruitment;
import team.retum.jobis.thirdparty.fcm.FCMUtil;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class RecruitmentEventHandler {

    private final QueryBookmarkPort queryBookmarkPort;
    private final CommandNotificationPort commandNotificationPort;
    private final FCMUtil fcmUtil;

    @Async("asyncTaskExecutor")
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onRecruitmentStatusChanged(RecruitmentStatusChangedEvent event) {
        List<Recruitment> doneRecruitments = event.getRecruitments().stream()
            .filter(recruitment -> recruitment.getRecruitingPeriod().endDate().isAfter(LocalDate.now()))
            .toList();
        Map<Long, List<BookmarkUserVO>> bookmarkUserMap = queryBookmarkPort.queryBookmarkUserByRecruitmentIds(
            doneRecruitments.stream().map(Recruitment::getId).toList()
        );
        for (Recruitment recruitment : doneRecruitments) {
            Notification repNotification = null;
            List<String> tokens = new ArrayList<>();
            for (BookmarkUserVO bookmarkUser : bookmarkUserMap.get(recruitment.getId())) {
                Notification notification = Notification.builder()
                    .title(bookmarkUser.getCompanyName())
                    .content("북마크한 " + bookmarkUser.getCompanyName() + " 모집의뢰서의 모집기간이 종료되었습니다.")
                    .userId(bookmarkUser.getUserId())
                    .topic(Topic.RECRUITMENT_DONE)
                    .detailId(recruitment.getId())
                    .authority(Authority.STUDENT)
                    .isNew(true)
                    .build();

                if (repNotification == null) {
                    repNotification = notification;
                }
                tokens.add(bookmarkUser.getToken());
                commandNotificationPort.saveNotification(notification);
            }
            fcmUtil.sendMessages(repNotification, tokens);
        }
    }
}
