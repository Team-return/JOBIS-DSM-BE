package team.retum.jobis.domain.recruitment.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.retum.jobis.domain.notification.model.Topic;
import team.retum.jobis.domain.recruitment.model.Recruitment;

import java.util.List;

@Getter
@AllArgsConstructor
public class InterestedRecruitmentEvent {

    private final Recruitment recruitments;
    private final List<Long> detailId;
    private final Topic topic;
    private final List<String> deviceTokens;
}
