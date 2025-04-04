package team.retum.jobis.domain.recruitment.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.retum.jobis.domain.recruitment.model.Recruitment;

@Getter
@AllArgsConstructor
public class InterestedRecruitmentEvent {

    private final Recruitment recruitments;
}
