package team.retum.jobis.domain.wintern.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.domain.wintern.spi.QueryWinternPort;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class QueryIsWinternUseCase {

    private final QueryWinternPort queryWinternPort;

    public boolean execute() {
        return queryWinternPort.getIsWintern();
    }
}
