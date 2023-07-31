package team.retum.jobis.domain.acceptance.usecase;

import team.retum.jobis.common.annotation.UseCase;
import team.retum.jobis.domain.acceptance.dto.request.ChangeContractDateRequest;
import team.retum.jobis.domain.acceptance.spi.CommandAcceptancePort;
import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.application.exception.InvalidDateException;

import java.time.LocalDate;

@RequiredArgsConstructor
@UseCase
public class ChangeContractDateService {

    private final CommandAcceptancePort commandAcceptancePort;

    public void execute(ChangeContractDateRequest request) {
        if (request.getContractDate().isBefore(LocalDate.now())) {
            throw InvalidDateException.EXCEPTION;
        }

        commandAcceptancePort.updateContractDate(
                request.getContractDate(),
                request.getAcceptanceIds()
        );
    }
}
