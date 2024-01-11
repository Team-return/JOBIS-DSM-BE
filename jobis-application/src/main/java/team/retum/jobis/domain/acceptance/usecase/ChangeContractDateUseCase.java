package team.retum.jobis.domain.acceptance.usecase;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.acceptance.dto.request.ChangeContractDateRequest;
import team.retum.jobis.domain.acceptance.spi.CommandAcceptancePort;
import team.retum.jobis.domain.application.exception.InvalidDateException;

import java.time.LocalDate;

@RequiredArgsConstructor
@UseCase
public class ChangeContractDateUseCase {

    private final CommandAcceptancePort commandAcceptancePort;

    public void execute(ChangeContractDateRequest request) {
        commandAcceptancePort.updateContractDate(
                request.getContractDate(),
                request.getAcceptanceIds()
        );
    }
}
