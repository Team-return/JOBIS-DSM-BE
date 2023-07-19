package com.example.jobisapplication.domain.acceptance.spi;

import com.example.jobisapplication.domain.acceptance.model.Acceptance;

import java.time.LocalDate;
import java.util.List;

public interface CommandAcceptancePort {

    void updateContractDate(LocalDate contractDate, List<Long> acceptanceIds);

    void saveAllAcceptance(List<Acceptance> acceptanceEntities);
}
