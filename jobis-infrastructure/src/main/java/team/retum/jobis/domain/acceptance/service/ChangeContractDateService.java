package team.retum.jobis.domain.acceptance.service;

import lombok.RequiredArgsConstructor;
import team.retum.jobis.domain.acceptance.persistence.repository.AcceptanceRepository;
import team.retum.jobis.domain.acceptance.presentation.dto.request.ChangeContractDateRequest;
import com.example.jobisapplication.domain.application.exception.InvalidDateException;
import com.example.jobisapplication.common.annotation.Service;

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