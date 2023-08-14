package team.retum.jobis.domain.company.persistence.repository.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import team.retum.jobis.domain.company.model.CompanyType;
import team.retum.jobis.domain.company.spi.vo.TeacherCompaniesVO;

@Getter
public class TeacherQueryCompaniesVO extends TeacherCompaniesVO {

    @QueryProjection
    public TeacherQueryCompaniesVO(Long companyId, String companyName, String mainAddress, String businessArea,
                                   Integer workersCount, double take, CompanyType companyType,
                                   Boolean convention, Boolean personalContact, Integer recentRecruitYear,
                                   Long totalAcceptanceCount, Long reviewCount) {
        super(companyId, companyName, mainAddress, businessArea,
                workersCount, take, companyType, convention,
                personalContact, recentRecruitYear, totalAcceptanceCount, reviewCount);
    }
}
