package team.retum.jobis.domain.intern.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.intern.model.WinterIntern;
import team.retum.jobis.domain.intern.spi.CommandWinterInternPort;
import team.retum.jobis.domain.intern.spi.QueryWinterInternPort;

@RequiredArgsConstructor
@UseCase
public class ToggleWinterInternUseCase {

    private final CommandWinterInternPort commandWinterInternPort;
    private final QueryWinterInternPort queryWinterInternPort;

    public void execute() {
        boolean toggled = !queryWinterInternPort.getIsWintern();

        WinterIntern toggledWinterIntern = WinterIntern.builder()
            .isWinterInterned(toggled)
            .build();

        commandWinterInternPort.save(toggledWinterIntern);
    }
}
