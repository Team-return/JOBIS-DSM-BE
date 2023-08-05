package team.retum.jobis.domain.acceptance.model;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.common.annotation.Aggregate;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@Aggregate
public class Acceptance {

    private final Long id;

    private final Integer year;

    private final String studentName;

    private final String businessArea;

    private final List<String> tech;

    private final String studentGcn;

    private final LocalDate contractDate;

    private final Long companyId;
}
