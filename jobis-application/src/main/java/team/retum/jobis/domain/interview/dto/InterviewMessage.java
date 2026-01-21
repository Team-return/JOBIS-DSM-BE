package team.retum.jobis.domain.interview.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.retum.jobis.domain.interview.model.InterviewTiming;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Getter
@AllArgsConstructor
public class InterviewMessage {

    private final String title;
    private final String content;

    public static InterviewMessage of(InterviewTiming timing, String companyName, String interviewTime, String location, LocalDate interviewDate) {
        return switch (timing) {
            case TODAY -> ofToday(companyName, interviewTime, location);
            case TOMORROW -> ofTomorrow(companyName, interviewTime, location);
            case THREE_DAYS_LATER -> ofThreeDaysLater(companyName, interviewTime, location, interviewDate);
            case AFTER_INTERVIEW -> ofAfterInterview(companyName);
        };
    }

    private static InterviewMessage ofToday(String companyName, String interviewTime, String location) {
        String title = "오늘 면접날이에요! 🎯";
        String content = String.format(
            "오늘 '%s'에서 면접이 예정되어 있어요.\n시간: %s\n장소: %s\n긴장하지 말고 자신감 있게 임하세요. 화이팅! 💪",
            companyName,
            interviewTime,
            location
        );
        return new InterviewMessage(title, content);
    }

    private static InterviewMessage ofTomorrow(String companyName, String interviewTime, String location) {
        String title = "내일 면접이 있어요! 🔔";
        String content = String.format(
            "내일 '%s'에서 면접이 예정되어 있어요.\n시간: %s\n장소: %s\n준비한 만큼 좋은 결과 있을 거예요. 응원할게요! 🍀",
            companyName,
            interviewTime,
            location
        );
        return new InterviewMessage(title, content);
    }

    private static InterviewMessage ofThreeDaysLater(String companyName, String interviewTime, String location, LocalDate interviewDate) {
        String title = "면접이 3일 남았어요! 📅";
        String formattedDate = interviewDate.format(DateTimeFormatter.ofPattern("M월 d일"));
        String content = String.format(
            "'%s' 면접이 3일 후에 있어요.\n일정: %s\n시간: %s\n장소: %s\n여기까지 잘 준비해온 만큼, 당신의 노력이 빛날 거예요! 🌟",
            companyName,
            formattedDate,
            interviewTime,
            location
        );
        return new InterviewMessage(title, content);
    }

    private static InterviewMessage ofAfterInterview(String companyName) {
        String title = "면접이 끝났어요! 😊";
        String content = String.format(
            "'%s' 면접은 잘 보셨나요?\n후배들을 위해 면접 후기를 작성해주세요! ✍️",
            companyName
        );
        return new InterviewMessage(title, content);
    }
}
