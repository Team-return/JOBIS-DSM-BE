package team.retum.jobis.domain.company.event;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RecentCompanyEvent {
    private final Integer userId;
    private final Integer companyId;
}
