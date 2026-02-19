package team.retum.jobis.domain.company.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RecentCompanyEvent {
    private final Long userId;
    private final Long companyId;
}
