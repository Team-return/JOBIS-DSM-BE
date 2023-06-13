package team.returm.jobis.domain.acceptance.service;

import lombok.RequiredArgsConstructor;
import team.returm.jobis.domain.acceptance.domain.repository.AcceptanceRepository;
import team.returm.jobis.domain.acceptance.presentation.dto.request.ChangeContractDateRequest;
import team.returm.jobis.domain.application.exception.InvalidDateException;
import team.returm.jobis.global.annotation.Service;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class ChangeContractDateService {

    private final AcceptanceRepository acceptanceRepository;

    public void execute(ChangeContractDateRequest request) {
        if (request.getContractDate().isBefore(LocalDate.now())) {
            throw InvalidDateException.EXCEPTION;
        }

        acceptanceRepository.updateContractDate(
                request.getContractDate(),
                request.getAcceptanceIds()
        );
    }
}
