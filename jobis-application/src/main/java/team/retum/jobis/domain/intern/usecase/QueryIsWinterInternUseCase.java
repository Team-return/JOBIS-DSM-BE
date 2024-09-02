package team.retum.jobis.domain.intern.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.ReadOnlyUseCase;
import team.retum.jobis.domain.intern.spi.QueryWinterInternPort;

@RequiredArgsConstructor
@ReadOnlyUseCase
public class QueryIsWinterInternUseCase {

    private final QueryWinterInternPort queryWinterInternPort;

    public boolean execute() {
        return queryWinterInternPort.getIsWintern();
    }
}
