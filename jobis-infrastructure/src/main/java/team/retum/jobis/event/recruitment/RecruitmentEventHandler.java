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
import team.retum.jobis.domain.recruitment.model.Recruitment;
import team.retum.jobis.domain.recruitment.spi.QueryRecruitAreaPort;
import team.retum.jobis.domain.student.model.Student;
import team.retum.jobis.domain.student.spi.QueryStudentPort;
import team.retum.jobis.domain.user.model.User;
import team.retum.jobis.domain.user.spi.QueryUserPort;
import team.retum.jobis.event.RabbitMqProducer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class RecruitmentEventHandler {

    private final QueryBookmarkPort queryBookmarkPort;
    private final CommandNotificationPort commandNotificationPort;
    private final QueryStudentPort queryStudentPort;
    private final QueryUserPort queryUserPort;
    private final QueryRecruitAreaPort queryRecruitAreaPort;
    private final RabbitMqProducer rabbitMqProducer;

    @Async("asyncTaskExecutor")
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void onRecruitmentStatusChanged(RecruitmentStatusChangedEvent event) {
        List<Recruitment> doneRecruitments = event.getRecruitments().stream()
            .filter(recruitment ->
                recruitment.getRecruitingPeriod() != null &&
                recruitment.getRecruitingPeriod().endDate().isAfter(LocalDate.now())
            )
            .toList();
        Map<Long, List<BookmarkUserVO>> bookmarkUserMap = queryBookmarkPort.getBookmarkUserByRecruitmentIds(
            doneRecruitments.stream().map(Recruitment::getId).toList()
        );
        for (Recruitment recruitment : doneRecruitments) {
            Notification repNotification = null;
            List<String> tokens = new ArrayList<>();

            List<BookmarkUserVO> bookmarkUserVOs = bookmarkUserMap.getOrDefault(recruitment.getId(), Collections.emptyList());
            for (BookmarkUserVO bookmarkUser : bookmarkUserVOs) {
                Notification notification = Notification.builder()
                    .title(bookmarkUser.getCompanyName())
                    .content("북마크한 " + bookmarkUser.getCompanyName() + " 모집의뢰서의 모집기간이 종료되었습니다.")
                    .userId(bookmarkUser.getUserId())
                    .deviceToken(bookmarkUser.getToken())
                    .topic(Topic.RECRUITMENT)
                    .detailId(recruitment.getId())
                    .authority(Authority.STUDENT)
                    .isNew(true)
                    .build();

                if (repNotification == null) {
                    repNotification = notification;
                }
                tokens.add(bookmarkUser.getToken());
                commandNotificationPort.save(notification);
                rabbitMqProducer.publishEvent(notification);
            }
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
                    .deviceToken(user.getToken())
                    .detailId(recruitment.getId())
                    .topic(Topic.RECRUITMENT)
                    .authority(Authority.STUDENT)
                    .isNew(true)
                    .build();

                if (repNotification == null) {
                    repNotification = notification;
                }

                tokens.add(user.getToken());
                commandNotificationPort.save(notification);
                rabbitMqProducer.publishEvent(notification);
            }
        }
    }
}
