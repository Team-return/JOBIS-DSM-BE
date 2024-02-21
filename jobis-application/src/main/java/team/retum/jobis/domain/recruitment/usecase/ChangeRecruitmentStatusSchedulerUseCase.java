package team.retum.jobis.domain.recruitment.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.common.spi.PublishNotificationEventPort;
import team.retum.jobis.domain.auth.model.Authority;
import team.retum.jobis.domain.bookmark.spi.QueryBookmarkPort;
import team.retum.jobis.domain.bookmark.spi.vo.BookmarkUserVO;
import team.retum.jobis.domain.notification.model.Notification;
import team.retum.jobis.domain.notification.model.Topic;
import team.retum.jobis.domain.notification.spi.CommandNotificationPort;
import team.retum.jobis.domain.recruitment.model.RecruitStatus;
import team.retum.jobis.domain.recruitment.model.Recruitment;
import team.retum.jobis.domain.recruitment.spi.CommandRecruitmentPort;
import team.retum.jobis.domain.recruitment.spi.QueryRecruitmentPort;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@UseCase
public class ChangeRecruitmentStatusSchedulerUseCase {
    private final CommandRecruitmentPort commandRecruitmentPort;
    private final QueryRecruitmentPort queryRecruitmentPort;
    private final QueryBookmarkPort queryBookmarkPort;
    private final CommandNotificationPort commandNotificationPort;
    private final PublishNotificationEventPort publishNotificationEventPort;

    public void execute() {
        List<Recruitment> recruitments = queryRecruitmentPort.queryAllRecruitments();

        commandRecruitmentPort.saveAllRecruitments(
                recruitments.stream()
                        .map(Recruitment::updateRecruitmentStatus)
                        .toList()
        );

        List<Recruitment> doneRecruitment = recruitments.stream()
                .filter(recruitment -> recruitment.getEndDate().isAfter(LocalDate.now()))
                .toList();
        Map<Long, List<BookmarkUserVO>> bookmarkUserMap = queryBookmarkPort.queryBookmarkUserByRecruitmentIds(
                doneRecruitment.stream().map(Recruitment::getId).toList()
        );

        for (Recruitment recruitment : recruitments) {
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

                if (repNotification == null) repNotification = notification;
                tokens.add(bookmarkUser.getToken());
                commandNotificationPort.saveNotification(notification);
            }
            publishNotificationEventPort.publishNotificationEvent(repNotification, tokens);
        }
    }
}
