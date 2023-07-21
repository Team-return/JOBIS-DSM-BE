package team.retum.jobis.domain.company.persistence.repository.vo;

import com.example.jobisapplication.domain.company.spi.vo.TeacherCompaniesVO;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import com.example.jobisapplication.domain.company.model.CompanyType;

@Getter
public class TeacherQueryCompaniesVO extends TeacherCompaniesVO {

    @QueryProjection
    public TeacherQueryCompaniesVO(Long companyId, String companyName, String mainAddress, String businessArea,
                                   Integer workersCount, double take, CompanyType companyType,
                                   Boolean convention, Boolean personalContact, Integer recentRecruitYear,
                                   Long totalAcceptanceCount) {
        super(companyId, companyName, mainAddress, businessArea,
                workersCount, take, companyType, convention,
                personalContact,recentRecruitYear, totalAcceptanceCount);
    }
}
