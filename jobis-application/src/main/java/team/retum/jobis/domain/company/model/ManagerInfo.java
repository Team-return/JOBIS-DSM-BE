package team.retum.jobis.domain.company.model;

import lombok.Builder;

@Builder
public record ManagerInfo(
        String managerName,
        String managerPhoneNo,
        String subManagerName,
        String subManagerPhoneNo
) {}
