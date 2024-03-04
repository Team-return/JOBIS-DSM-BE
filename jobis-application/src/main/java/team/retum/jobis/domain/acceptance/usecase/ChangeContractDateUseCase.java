package team.retum.jobis.domain.acceptance.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.acceptance.spi.CommandAcceptancePort;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@UseCase
public class ChangeContractDateUseCase {

    private final CommandAcceptancePort commandAcceptancePort;

    public void execute(List<Long> acceptanceIds, LocalDate contractDate) {
        commandAcceptancePort.updateContractDate(contractDate, acceptanceIds);
    }
}
