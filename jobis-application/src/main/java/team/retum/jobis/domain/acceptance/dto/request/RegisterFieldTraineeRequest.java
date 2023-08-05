package team.retum.jobis.domain.acceptance.dto.request;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class RegisterFieldTraineeRequest {

    private List<Long> applicationIds;

    private LocalDate startDate;

    private LocalDate endDate;
}
