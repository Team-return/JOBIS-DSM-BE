package com.example.jobisapplication.domain.acceptance.spi;

import com.example.jobisapplication.domain.acceptance.model.Acceptance;

import java.util.List;

public interface QueryAcceptancePort {

    List<Acceptance> queryAcceptancesByCompanyIdAndYear(Long companyId, Integer year);

}
