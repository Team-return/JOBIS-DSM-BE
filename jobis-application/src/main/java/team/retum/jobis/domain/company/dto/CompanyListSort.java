package team.retum.jobis.domain.company.dto;

import team.retum.jobis.common.dto.request.OrderBy;

public record CompanyListSort(CompanySortType sortType, OrderBy orderBy) {
}
