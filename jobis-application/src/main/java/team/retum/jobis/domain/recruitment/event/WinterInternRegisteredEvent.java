package team.retum.jobis.domain.recruitment.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.retum.jobis.domain.recruitment.model.Recruitment;

import java.util.List;

@Getter
@AllArgsConstructor
public class WinterInternRegisteredEvent {

    private final List<Recruitment> recruitments;
}
