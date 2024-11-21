package team.retum.jobis.domain.intern.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.retum.jobis.domain.recruitment.model.Recruitment;

@Getter
@AllArgsConstructor
public class SingleWinterInternRegisteredEvent {

    private final Recruitment recruitments;

}
