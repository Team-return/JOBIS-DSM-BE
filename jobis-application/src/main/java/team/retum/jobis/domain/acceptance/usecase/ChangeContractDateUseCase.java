package team.retum.jobis.domain.acceptance.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.acceptance.dto.request.ChangeContractDateRequest;
import team.retum.jobis.domain.acceptance.spi.CommandAcceptancePort;

@RequiredArgsConstructor
@UseCase
public class ChangeContractDateUseCase {

    private final CommandAcceptancePort commandAcceptancePort;

    public void execute(ChangeContractDateRequest request) {
        commandAcceptancePort.updateContractDate(
                request.contractDate(),
                request.acceptanceIds()
        );
    }
}
