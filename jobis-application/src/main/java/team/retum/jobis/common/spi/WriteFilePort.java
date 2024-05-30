package team.retum.jobis.common.spi;

import team.retum.jobis.domain.company.spi.vo.CompanyDetailsVO;
import team.retum.jobis.domain.company.spi.vo.TeacherCompaniesVO;
import team.retum.jobis.domain.recruitment.spi.vo.TeacherRecruitmentVO;

import java.util.List;

public interface WriteFilePort {
    byte[] writeRecruitmentExcelFile(List<TeacherRecruitmentVO> recruitmentList);

    byte[] writeCompanyExcelFile(List<TeacherCompaniesVO> companyList);
}
