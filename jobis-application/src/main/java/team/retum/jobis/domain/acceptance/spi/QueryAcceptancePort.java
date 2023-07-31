package team.retum.jobis.domain.acceptance.spi;

import team.retum.jobis.domain.acceptance.model.Acceptance;

import java.util.List;

public interface QueryAcceptancePort {

    List<Acceptance> queryAcceptancesByCompanyIdAndYear(Long companyId, Integer year);

}
