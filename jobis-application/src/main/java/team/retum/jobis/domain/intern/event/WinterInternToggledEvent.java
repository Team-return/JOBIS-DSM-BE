package team.retum.jobis.domain.intern.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import team.retum.jobis.domain.intern.model.WinterIntern;

@Getter
@AllArgsConstructor
public class WinterInternToggledEvent {

    private final WinterIntern winterIntern;
}