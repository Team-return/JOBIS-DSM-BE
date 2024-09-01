package team.retum.jobis.domain.wintern.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.wintern.model.Wintern;
import team.retum.jobis.domain.wintern.spi.CommandWinternPort;
import team.retum.jobis.domain.wintern.spi.QueryWinternPort;

@RequiredArgsConstructor
@UseCase
public class ToggleWinternUseCase {

    private final CommandWinternPort commandWinternPort;
    private final QueryWinternPort queryWinternPort;

    public void execute() {
        boolean current = queryWinternPort.getIsWintern();
        boolean toggled = !current;

        Wintern toggledWintern = Wintern.builder()
            .isWinterInterned(toggled)
            .build();

        commandWinternPort.save(toggledWintern);
    }
}
