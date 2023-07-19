package com.example.jobisapplication.domain.acceptance.usecase;

import com.example.jobisapplication.common.annotation.UseCase;
import com.example.jobisapplication.domain.acceptance.dto.request.ChangeContractDateRequest;
import com.example.jobisapplication.domain.acceptance.spi.CommandAcceptancePort;
import lombok.RequiredArgsConstructor;
import com.example.jobisapplication.domain.application.exception.InvalidDateException;

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
