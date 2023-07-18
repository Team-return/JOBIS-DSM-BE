package team.retum.jobis.domain.company.persistence.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import com.example.jobisapplication.domain.company.model.CompanyType;

@Getter
public class TeacherQueryCompaniesVO {

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

    @QueryProjection
    public TeacherQueryCompaniesVO(Long companyId, String companyName, String mainAddress, String businessArea,
                                   Integer workersCount, double take, CompanyType companyType,
                                   Boolean convention, Boolean personalContact, Integer recentRecruitYear,
                                   Long totalAcceptanceCount) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.mainAddress = mainAddress;
        this.businessArea = businessArea;
        this.workersCount = workersCount;
        this.take = take;
        this.companyType = companyType;
        this.convention = convention;
        this.personalContact = personalContact;
        this.recentRecruitYear = recentRecruitYear;
        this.totalAcceptanceCount = totalAcceptanceCount;
    }
}
