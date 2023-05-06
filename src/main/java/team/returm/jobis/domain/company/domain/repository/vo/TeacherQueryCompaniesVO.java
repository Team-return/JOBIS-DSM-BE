package team.returm.jobis.domain.company.domain.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import team.returm.jobis.domain.company.domain.enums.CompanyType;

@Getter
public class TeacherQueryCompaniesVO {

    private final Long companyId;
    private final String companyName;
    private final String mainAddress;
    private final String businessArea;
    private final Integer workersCount;
    private final Integer sales;
    private final CompanyType companyType;
    private final Integer recentRecruitYear;
    private final Long totalAcceptanceCount;

    @QueryProjection
    public TeacherQueryCompaniesVO(Long companyId, String companyName, String mainAddress, String businessArea,
                                   Integer workersCount, Integer sales, CompanyType companyType,
                                   Integer recentRecruitYear, Long totalAcceptanceCount) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.mainAddress = mainAddress;
        this.businessArea = businessArea;
        this.workersCount = workersCount;
        this.sales = sales;
        this.companyType = companyType;
        this.recentRecruitYear = recentRecruitYear;
        this.totalAcceptanceCount = totalAcceptanceCount;
    }
}