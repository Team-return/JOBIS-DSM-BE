package team.retum.jobis.domain.company.spi.vo;

import team.retum.jobis.domain.company.model.CompanyType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TeacherCompaniesVO {

    private final Long companyId;
    private final String companyName;
    private final String mainAddress;
    private final String businessArea;
    private final Integer workersCount;
    private final double take;
    private final CompanyType companyType;
    private final Boolean convention;
    private final Boolean personalContact;
    private final Integer recentRecruitYear;
    private final Long totalAcceptanceCount;
}
