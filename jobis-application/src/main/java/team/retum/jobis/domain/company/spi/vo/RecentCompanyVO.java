package team.retum.jobis.domain.company.spi.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import team.retum.jobis.domain.recruitment.model.RecruitStatus;

@Getter
@NoArgsConstructor(force = true)
@AllArgsConstructor
public class RecentCompanyVO {

    private final Long companyId;
    private final String companyName;
    private final Boolean isRecruiting;
    private final String companyLogoUrl;
}
