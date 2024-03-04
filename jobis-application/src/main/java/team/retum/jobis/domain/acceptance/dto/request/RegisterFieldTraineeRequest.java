package team.retum.jobis.domain.acceptance.dto.request;

import java.time.LocalDate;
import java.util.List;

public record RegisterFieldTraineeRequest(
        List<Long> applicationIds,
        LocalDate startDate,
        LocalDate endDate
) {}