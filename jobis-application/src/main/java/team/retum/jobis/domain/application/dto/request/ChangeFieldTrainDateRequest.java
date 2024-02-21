package team.retum.jobis.domain.application.dto.request;

import java.time.LocalDate;
import java.util.List;

public record ChangeFieldTrainDateRequest(
        List<Long> applicationIds,
        LocalDate startDate,
        LocalDate endDate
) {}