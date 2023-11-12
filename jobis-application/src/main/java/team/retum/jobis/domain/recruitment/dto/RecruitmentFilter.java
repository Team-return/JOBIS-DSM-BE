package team.retum.jobis.domain.recruitment.dto;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.domain.recruitment.model.RecruitStatus;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class RecruitmentFilter {

    private final Integer year;

    private final LocalDate startDate;

    private final LocalDate endDate;

    private final RecruitStatus status;

    private final String companyName;

    private final Long page;

    private final List<Long> codes;

    private final Long studentId;

    private final Long jobCode;

    @Builder.Default
    private int limit = 11;

    public Long getOffset() {
        return page * limit;
    }
}
