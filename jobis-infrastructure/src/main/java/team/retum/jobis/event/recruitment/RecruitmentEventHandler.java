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
import team.retum.jobis.domain.recruitment.event.InterestedRecruitmentEvent;
import team.retum.jobis.domain.recruitment.event.RecruitmentStatusChangedEvent;
import team.retum.jobis.domain.recruitment.event.WinterInternRegisteredEvent;
import team.retum.jobis.domain.recruitment.model.Recruitment;
import team.retum.jobis.domain.recruitment.spi.QueryRecruitAreaPort;
import team.retum.jobis.domain.student.model.Student;
import team.retum.jobis.domain.student.spi.QueryStudentPort;
import team.retum.jobis.domain.user.model.User;
import team.retum.jobis.domain.user.spi.QueryUserPort;
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
    private final QueryStudentPort queryStudentPort;
    private final QueryUserPort queryUserPort;
    private final QueryRecruitAreaPort queryRecruitAreaPort;

    @Async("asyncTaskExecutor")
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onRecruitmentStatusChanged(RecruitmentStatusChangedEvent event) {
        List<Recruitment> doneRecruitments = event.getRecruitments().stream()
            .filter(recruitment -> recruitment.getRecruitingPeriod().endDate().isAfter(LocalDate.now()))
            .toList();
        Map<Long, List<BookmarkUserVO>> bookmarkUserMap = queryBookmarkPort.getBookmarkUserByRecruitmentIds(
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
                commandNotificationPort.save(notification);
            }
            fcmUtil.sendMessages(repNotification, tokens);
        }
    }

    @Async("asyncTaskExecutor")
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onInterestCodeMatched(InterestedRecruitmentEvent event) {
        Recruitment recruitment = event.getRecruitments();
        List<Long> recruitmentCodes = queryRecruitAreaPort.getCodesByRecruitmentId(recruitment.getId());

        List<Student> interestedStudents = queryStudentPort.getByInterestCode(recruitmentCodes);

        if (!interestedStudents.isEmpty()) {
            Notification repNotification = null;
            List<String> tokens = new ArrayList<>();

            for (Student student : interestedStudents) {
                User user = queryUserPort.getByStudentId(student.getId());

                Notification notification = Notification.builder()
                    .title("모집의뢰서 보러가기")
                    .content(student.getName() + " 님이 관심 있을 만한 모집의뢰서가 추가되었어요!")
                    .userId(user.getId())
                    .detailId(recruitment.getId())
                    .topic(Topic.NEW_INTERESTED_RECRUITMENT)
                    .authority(Authority.STUDENT)
                    .isNew(true)
                    .build();

                if (repNotification == null) {
                    repNotification = notification;
                }
                tokens.add(user.getToken());
                commandNotificationPort.save(notification);
            }

            fcmUtil.sendMessages(repNotification, tokens);
        }
    }

    @Async("asyncTaskExecutor")
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onWinterInternRegistered(WinterInternRegisteredEvent event) {
        Recruitment recruitment = event.getRecruitment();

        if (recruitment.isWinterIntern()) {
            List<String> deviceTokens = queryUserPort.getDeviceTokenByTopic(Topic.WINTER_INTERN_REGISTERED);

            deviceTokens.forEach(deviceToken -> {
                User user = queryUserPort.getUserIdByDeviceToken(deviceToken);

                Notification notification = Notification.builder()
                    .title("겨울 인턴 모집 안내")
                    .content("새로운 겨울 인턴형 모집의뢰서가 등록되었습니다. 확인해보세요!")
                    .userId(user.getId())
                    .detailId(recruitment.getId())
                    .topic(Topic.WINTER_INTERN_REGISTERED)
                    .authority(Authority.STUDENT)
                    .isNew(true)
                    .build();

                commandNotificationPort.save(notification);

                fcmUtil.sendMessageToTopic(notification);
            });
        }
    }
}
