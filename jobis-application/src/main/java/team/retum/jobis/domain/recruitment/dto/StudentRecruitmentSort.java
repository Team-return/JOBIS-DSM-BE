package team.retum.jobis.domain.recruitment.dto;

import lombok.Builder;
import lombok.Getter;
import team.retum.jobis.common.dto.request.OrderBy;
import team.retum.jobis.domain.recruitment.dto.request.RecruitSortType;

@Getter
@Builder
public class StudentRecruitmentSort {
    private final RecruitSortType sortType;

    private final OrderBy orderBy;
}
