package team.retum.jobis.domain.acceptance.spi;

import team.retum.jobis.domain.acceptance.model.Acceptance;

import java.time.LocalDate;
import java.util.List;

public interface CommandAcceptancePort {

    void updateContractDate(LocalDate contractDate, List<Long> acceptanceIds);

    void saveAll(List<Acceptance> acceptanceEntities);
}
