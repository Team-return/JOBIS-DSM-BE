package team.retum.jobis.domain.acceptance.dto.request;

import java.time.LocalDate;
import java.util.List;


public record ChangeContractDateRequest(
        List<Long> acceptanceIds,
        LocalDate contractDate
) {}